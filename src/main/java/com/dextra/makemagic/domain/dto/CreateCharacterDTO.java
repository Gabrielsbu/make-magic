package com.dextra.makemagic.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateCharacterDTO {

    private String name;
    private String role;
    private String school;

    @NotEmpty
    private String house;
    private String patronus;
}
