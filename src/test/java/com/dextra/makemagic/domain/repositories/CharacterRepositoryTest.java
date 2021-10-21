package com.dextra.makemagic.domain.repositories;

import com.dextra.makemagic.domain.models.Character;
import com.dextra.makemagic.domain.utils.CreateCharacter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Tests to Character Repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration
class CharacterRepositoryTest {

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    @DisplayName("Save a character when Successful")
    void save_PersistCharacter_WhenSuccessful() {
        Character characterCreated = CreateCharacter.createCharacter();

        Character characterSaved = characterRepository.save(characterCreated);

        Assertions.assertThat(characterSaved).isExactlyInstanceOf(Character.class).isNotNull();
        Assertions.assertThat(characterSaved.getHouse()).isEqualTo(characterCreated.getHouse());
        Assertions.assertThat(characterSaved.getName()).isEqualTo(characterCreated.getName()).isExactlyInstanceOf(String.class);
        Assertions.assertThat(characterSaved.getRole()).isNotEmpty().isNotNull().isExactlyInstanceOf(String.class);
        Assertions.assertThat(characterSaved.getPatronus()).isEqualTo(characterCreated.getPatronus());
        Assertions.assertThat(characterSaved.getSchool()).isEqualTo(characterCreated.getSchool());

        Assertions.assertThat(characterSaved.getCharacterId()).isExactlyInstanceOf(Long.class).isNotNegative().isNotNull();
    }

    @Test
    @DisplayName("Find character by id, when successful")
    void findById_ReturnCharacter_WhenSuccessful(){
        Character characterToBeSaved = CreateCharacter.createCharacter();
        Character characterSaved = characterRepository.save(characterToBeSaved);

        Character characterSearch = characterRepository.findById(characterSaved.getCharacterId()).get();

        Assertions.assertThat(characterSearch).isExactlyInstanceOf(Character.class);
        Assertions.assertThat(characterSearch.getCharacterId()).isEqualTo(characterSaved.getCharacterId());
        Assertions.assertThat(characterSearch.getCharacterId()).isNotNull().isNotNegative().isExactlyInstanceOf(Long.class);
    }

    @Test
    @DisplayName("Delete character by id, when successful")
    void deleteById_returnVoid_WhenSuccessful() {
        Character characterToBeSaved = CreateCharacter.createCharacter();
        Character characterSaved = characterRepository.save(characterToBeSaved);

        characterRepository.deleteById(characterSaved.getCharacterId());

        Optional<Character> character = characterRepository.findById(characterSaved.getCharacterId());

        Assertions.assertThat(character.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Return a Constraint Violation Exception, when persist with house empty ou null")
    public void save_throwConstraintViolationException_whenHouseIsEmpty() {

        Character character = CreateCharacter.createCharacter();
        character.setHouse("");

        Assertions.assertThatThrownBy(() -> characterRepository.save(character))
                .isInstanceOf(ConstraintViolationException.class);
    }
}