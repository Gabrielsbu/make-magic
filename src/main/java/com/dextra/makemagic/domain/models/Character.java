package com.dextra.makemagic.domain.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity(name = "characters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID characterId;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_role")
    private String role;

    @Column(name = "character_school")
    private String school;

    @Column(name = "character_house")
    private String house;

    @Column(name = "character_patronus")
    private String patronus;

    private LocalDateTime createdCharacterAt;
    private LocalDateTime updatedCharacterAt;

    @PrePersist
    public void prePersist() {
        this.createdCharacterAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedCharacterAt = LocalDateTime.now();
    }
}
