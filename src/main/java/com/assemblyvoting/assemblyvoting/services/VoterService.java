package com.assemblyvoting.assemblyvoting.services;

import com.assemblyvoting.assemblyvoting.models.VoterModel;
import com.assemblyvoting.assemblyvoting.repositories.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
