package com.assemblyvoting.assemblyvoting.controllers;

import com.assemblyvoting.assemblyvoting.dtos.VoterDTO;
import com.assemblyvoting.assemblyvoting.models.VoterModel;
import com.assemblyvoting.assemblyvoting.services.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
}
