package com.assemblyvoting.assemblyvoting.services;

import com.assemblyvoting.assemblyvoting.models.VoterModel;
import com.assemblyvoting.assemblyvoting.repositories.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    public boolean existsByVoterCPF(String voterCPF) {
        return voterRepository.existsByVoterCPF(voterCPF);
    }

    @Transactional
    public Object save(VoterModel voterModel) {
        return voterRepository.save(voterModel);
    }

    public Page<VoterModel> findAll(Pageable pageable) {
        return voterRepository.findAll(pageable);
    }

    public Optional<VoterModel> findByVoterCPF(String cpf) {
        return voterRepository.findByVoterCPF(cpf);
    }

    @Transactional
    public void delete(VoterModel voterModel) {
        voterRepository.delete(voterModel);
    }
}
