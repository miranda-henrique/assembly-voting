package com.assemblyvoting.assemblyvoting.controllers;

import com.assemblyvoting.assemblyvoting.dtos.VoterDTO;
import com.assemblyvoting.assemblyvoting.dtos.VoterUpdateDTO;
import com.assemblyvoting.assemblyvoting.models.VoterModel;
import com.assemblyvoting.assemblyvoting.services.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    private VoterService voterService;

    @PostMapping()
    public ResponseEntity<Object> addVoter(@RequestBody @Valid VoterDTO voterDTO) {
        if (voterService.existsByVoterCPF(voterDTO.getVoterCPF())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF already registered.");
        }

        var voterModel = new VoterModel();

        BeanUtils.copyProperties(voterDTO, voterModel);
        voterModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(voterService.save(voterModel));
    }

    @GetMapping()
    public ResponseEntity<Page<VoterModel>> getAllVoters(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.findAll(pageable));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> getOneVoter(@PathVariable(value = "cpf") String cpf) {
        Optional<VoterModel> voterModelOptional = voterService.findByVoterCPF(cpf);

        if (!voterModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(voterModelOptional.get());
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Object> updateVoter(
            @PathVariable(value = "cpf") String cpf,
            @RequestBody @Valid VoterUpdateDTO voterUpdateDTO
    ) {
        Optional<VoterModel> voterModelOptional = voterService.findByVoterCPF(cpf);

        if (!voterModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF not found.");
        }

        var voterModel = new VoterModel();

        BeanUtils.copyProperties(voterUpdateDTO, voterModel);
        voterModel.setId(voterModelOptional.get().getId());
        voterModel.setVoterCPF(voterModelOptional.get().getVoterCPF());
        voterModel.setRegistrationDate(voterModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(voterService.save(voterModel));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object> deleteVoter(@PathVariable(value = "cpf") String cpf) {
        Optional<VoterModel> voterModelOptional = voterService.findByVoterCPF(cpf);

        if (!voterModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF not found.");
        }

        voterService.delete(voterModelOptional.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
