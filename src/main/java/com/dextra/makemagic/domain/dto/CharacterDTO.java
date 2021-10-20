package com.dextra.makemagic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private Long characterId;
    private String name;
    private String role;
    private String school;
    private String house;
    private String patronus;

    private LocalDateTime createdCharacterAt;
}
