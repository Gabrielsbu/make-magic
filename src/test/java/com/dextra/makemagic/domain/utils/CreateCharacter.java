package com.dextra.makemagic.domain.utils;

import com.dextra.makemagic.domain.dto.CreateCharacterDTO;
import com.dextra.makemagic.domain.dto.UpdatedCharacterDTO;
import com.dextra.makemagic.domain.models.Character;

import java.time.LocalDateTime;

public class CreateCharacter {

    public static Character createCharacter(){

        Character character = new Character();

        character.setName("Nome do personagem");
        character.setSchool("Escola do teste");
        character.setHouse("1b48062a-2b1c-4918-b450-5fb4342429f0");
        character.setRole("Role do usuario");
        character.setPatronus("Poder do personagem");

        return character;
    }

    public static Character characterCreated(){

        Character character = new Character();

        character.setCharacterId(1L);
        character.setName("Nome do personagem");
        character.setSchool("Escola do teste");
        character.setHouse("1b48062a-2b1c-4918-b450-5fb4342429f0");
        character.setRole("Role do usuario");
        character.setPatronus("Poder do personagem");

        return character;
    }

    public static CreateCharacterDTO characterCreatedDTO(Character characterRequest){

        CreateCharacterDTO character = new CreateCharacterDTO();

        character.setName(characterRequest.getName());
        character.setSchool(characterRequest.getSchool());
        character.setHouse(characterRequest.getHouse());
        character.setRole(characterRequest.getRole());
        character.setPatronus(characterRequest.getPatronus());

        return character;
    }

    public static UpdatedCharacterDTO updatedCharacter() {

        UpdatedCharacterDTO updatedCharacterDTO = new UpdatedCharacterDTO();
        updatedCharacterDTO.setName("Doação de animais");
        updatedCharacterDTO.setSchool("Gabriel Maia Ramos");
        updatedCharacterDTO.setRole("Role de alteração");
        updatedCharacterDTO.setHouse("56cabe3a-9bce-4b83-ba63-dcd156e9be45");
        updatedCharacterDTO.setPatronus("patronus de alteração");

        return updatedCharacterDTO;
    }

    public static Character editCharacter(UpdatedCharacterDTO updatedCharacterDTO) {

        Character character = new Character();
        character.setName(updatedCharacterDTO.getName());
        character.setSchool(updatedCharacterDTO.getSchool());
        character.setRole(updatedCharacterDTO.getRole());
        character.setHouse(updatedCharacterDTO.getHouse());
        character.setPatronus(updatedCharacterDTO.getPatronus());
        character.setUpdatedCharacterAt(LocalDateTime.now());

        return character;
    }
}
