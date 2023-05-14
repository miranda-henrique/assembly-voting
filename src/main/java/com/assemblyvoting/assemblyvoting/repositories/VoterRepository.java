package com.assemblyvoting.assemblyvoting.repositories;

import com.assemblyvoting.assemblyvoting.models.VoterModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoterRepository extends JpaRepository<VoterModel, UUID> {

    boolean existsByVoterCPF(String voterCPF);
}
