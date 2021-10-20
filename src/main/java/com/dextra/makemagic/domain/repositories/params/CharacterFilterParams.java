package com.dextra.makemagic.domain.repositories.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterFilterParams {

    private String name;
    private String school;
    private String role;
    private String house;
    private String patronus;
}
