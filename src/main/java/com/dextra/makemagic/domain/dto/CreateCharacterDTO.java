package com.dextra.makemagic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCharacterDTO {

    private String name;
    private String role;
    private String school;
    private String house;
    private String patronus;
}
