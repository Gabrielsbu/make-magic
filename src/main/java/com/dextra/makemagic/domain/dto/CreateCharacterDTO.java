package com.dextra.makemagic.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
