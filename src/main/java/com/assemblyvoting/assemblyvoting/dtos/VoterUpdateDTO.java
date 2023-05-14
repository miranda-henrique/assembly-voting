package com.assemblyvoting.assemblyvoting.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VoterUpdateDTO {

    @NotBlank
    @NotNull
    @Size(min = 2, max = 30)
    private String voterFirstName;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50)
    private String voterLastName;
}
