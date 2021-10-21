package com.dextra.makemagic.domain.integration;

import com.dextra.makemagic.domain.dto.CreateCharacterDTO;
import com.dextra.makemagic.domain.models.Character;
import com.dextra.makemagic.domain.repositories.CharacterRepository;
import com.dextra.makemagic.domain.utils.CreateCharacter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CharacterControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    @DisplayName("Find Character by id, when successful")
    void findCharacterById_ReturnCharacterById_WhenSuccessful(){
        Character characterToBeSaved = characterRepository.save(CreateCharacter.createCharacter());

        Long expectedId = characterToBeSaved.getCharacterId();

        Character character = testRestTemplate.getForObject("/v1/characters/{id}", Character.class, expectedId);

        Assertions.assertThat(character).isNotNull();
        Assertions.assertThat(character).isNotNull().isExactlyInstanceOf(Character.class);
        Assertions.assertThat(character.getHouse()).isEqualTo(characterToBeSaved.getHouse());
        Assertions.assertThat(character.getName()).isEqualTo(characterToBeSaved.getName());
        Assertions.assertThat(character.getPatronus()).isEqualTo(characterToBeSaved.getPatronus());
    }

    @Test
    @DisplayName("Save character, when successful")
    void saveCharacter_ReturnCharacterSaved_WhenSuccessful() {
        Character characterToBeSaved = CreateCharacter.createCharacter();

        CreateCharacterDTO characterDTO = CreateCharacter.characterCreatedDTO(characterToBeSaved);

        ResponseEntity<Character> character = testRestTemplate.postForEntity("/v1/characters", characterDTO, Character.class);

       Assertions.assertThat(character).isNotNull();
       Assertions.assertThat(character.getStatusCode()).isEqualTo(HttpStatus.OK);
       Assertions.assertThat(Objects.requireNonNull(character.getBody()).getCharacterId()).isNotNull();

    }

    @Test
    @DisplayName("Update character, when successful")
    void updateCharacter_ReturnCharacterUpdated_WhenSuccessful() {
        Character character = characterRepository.save(CreateCharacter.createCharacter());

        character.setName("teste se mudou");

        ResponseEntity<Void> characterToUpdated = testRestTemplate.exchange("/v1/characters/{id}",
                HttpMethod.PUT, new HttpEntity<>(character), Void.class, character.getCharacterId());

        Assertions.assertThat(characterToUpdated).isNotNull();
        Assertions.assertThat(characterToUpdated.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("Delete character by, when thrown by all exception")
    void deleteCharacter_ReturnNotFound_WhenThrownByAllException() {
        Character character = characterRepository.save(CreateCharacter.createCharacter());

        ResponseEntity<Void> characterToDeleted = testRestTemplate.exchange("/v1/characters/{id}",
                HttpMethod.DELETE, null, Void.class, character.getCharacterId());

        Assertions.assertThat(characterToDeleted).isNotNull();
        Assertions.assertThat(characterToDeleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
