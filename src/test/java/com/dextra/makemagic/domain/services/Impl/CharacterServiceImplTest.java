package com.dextra.makemagic.domain.services.Impl;

import com.dextra.makemagic.domain.dto.CharacterDTO;
import com.dextra.makemagic.domain.dto.CreateCharacterDTO;
import com.dextra.makemagic.domain.dto.UpdatedCharacterDTO;
import com.dextra.makemagic.domain.models.Character;
import com.dextra.makemagic.domain.repositories.CharacterRepository;
import com.dextra.makemagic.domain.utils.CreateCharacter;
import com.dextra.makemagic.exceptions.AllException;
import com.dextra.makemagic.utils.CharacterConverter;
import com.dextra.makemagic.utils.CharacterConverterImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class CharacterServiceImplTest {

    @InjectMocks
    private CharacterServiceImpl characterServiceImplMock;

    @Mock
    private CharacterRepository characterRepository;

    @Spy
    private CharacterConverter characterConverter = new CharacterConverterImpl();

    @Test
    @DisplayName("Delete character by id, when successful")
    void deleteCharacter_ReturnCharacterDeleted_WhenSuccessful() {
        Character characterCreated = CreateCharacter.characterCreated();

        BDDMockito.when(characterRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(characterCreated));

        BDDMockito.doNothing().when(characterRepository).deleteById(characterCreated.getCharacterId());

        Assertions.assertThatCode(() -> characterServiceImplMock.deleteCharacter(characterCreated.getCharacterId()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = characterServiceImplMock.deleteCharacter(characterCreated.getCharacterId());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Save character, when successful")
    void saveCharacter_ReturnCharacterSaved_WhenSuccessful() {
        Character characterToBeSaved = CreateCharacter.createCharacter();

        CreateCharacterDTO characterDTO = CreateCharacter.characterCreatedDTO(characterToBeSaved);

        BDDMockito.when(characterRepository.save(ArgumentMatchers.any(Character.class)))
                .thenReturn(characterToBeSaved);

        CharacterDTO characterSaved = characterServiceImplMock.saveCharacter(characterDTO);

        Assertions.assertThat(characterSaved).isNotNull().isExactlyInstanceOf(CharacterDTO.class);
        Assertions.assertThat(characterSaved.getHouse()).isEqualTo(characterToBeSaved.getHouse());
        Assertions.assertThat(characterSaved.getHouse()).isEqualTo(characterToBeSaved.getHouse());
        Assertions.assertThat(characterSaved.getName()).isEqualTo(characterToBeSaved.getName());
        Assertions.assertThat(characterSaved.getPatronus()).isEqualTo(characterToBeSaved.getPatronus());
    }

    @Test
    @DisplayName("Find Character by id, when successful")
    void findCharacterById_ReturnCharacterById_WhenSuccessful(){
        Character character = CreateCharacter.createCharacter();

        BDDMockito.when(characterRepository.save(ArgumentMatchers.any(Character.class)))
                .thenReturn(character);

        BDDMockito.when(characterRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(character));

        CharacterDTO characterExistent = characterServiceImplMock.findCharacterById(Objects.requireNonNull(character).getCharacterId());

        Assertions.assertThat(characterExistent).isNotNull().isExactlyInstanceOf(CharacterDTO.class);
        Assertions.assertThat(characterExistent.getHouse()).isEqualTo(character.getHouse());
        Assertions.assertThat(characterExistent.getName()).isEqualTo(character.getName());
        Assertions.assertThat(characterExistent.getPatronus()).isEqualTo(character.getPatronus());
    }

    @Test
    @DisplayName("Updated character, when successful")
    void updateCharacter_ReturnCharacterUpdated_WhenSuccessful() {

        Character character = CreateCharacter.characterCreated();
        UpdatedCharacterDTO updatedCharacterDTO = CreateCharacter.updatedCharacter();

        Character characterToUpdated = CreateCharacter.editCharacter(updatedCharacterDTO);


        BDDMockito.when(characterRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(character));


        BDDMockito.when(characterRepository.save(ArgumentMatchers.any(Character.class)))
                .thenReturn(characterToUpdated);


        CharacterDTO characterUpdate = characterServiceImplMock.updateCharacter(character.getCharacterId(), updatedCharacterDTO);

        Assertions.assertThat(characterUpdate).isNotNull().isExactlyInstanceOf(CharacterDTO.class);
        Assertions.assertThat(characterUpdate.getName()).isEqualTo(characterToUpdated.getName());
        Assertions.assertThat(characterUpdate.getHouse()).isEqualTo(characterToUpdated.getHouse());
        Assertions.assertThat(characterUpdate.getSchool()).isEqualTo(characterToUpdated.getSchool());

    }

    @Test
    @DisplayName("Delete character by, when thrown by all exception")
    void findCharacterById_ReturnNotFound_WhenThrownByAllException() {
        Character characterToCreated = CreateCharacter.characterCreated();

        BDDMockito.when(characterRepository.findById(22L)).thenReturn(Optional.of(characterToCreated));

        Assertions.assertThatThrownBy(() -> characterServiceImplMock.findCharacterById(characterToCreated.getCharacterId()))
                .isInstanceOf(AllException.class);
    }

    @Test
    @DisplayName("Delete character by, when thrown by all exception")
    void deleteCharacter_ReturnNotFound_WhenThrownByAllException() {
        Character characterToCreated = CreateCharacter.characterCreated();

        BDDMockito.when(characterRepository.findById(22L)).thenReturn(Optional.of(characterToCreated));

        Assertions.assertThatThrownBy(() -> characterServiceImplMock.deleteCharacter(characterToCreated.getCharacterId()))
                .isInstanceOf(AllException.class);
    }

}