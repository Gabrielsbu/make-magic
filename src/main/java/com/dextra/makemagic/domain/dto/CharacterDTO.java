package com.dextra.makemagic.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CharacterDTO {

    private Long characterId;
    private String name;
    private String role;
    private String school;
    private String house;
    private String patronus;

    private LocalDateTime createdCharacterAt;
}
