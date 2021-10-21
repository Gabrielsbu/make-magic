package com.dextra.makemagic.domain.services;

import com.dextra.makemagic.domain.dto.CharacterDTO;
import com.dextra.makemagic.domain.dto.CreateCharacterDTO;
import com.dextra.makemagic.domain.dto.UpdatedCharacterDTO;
import com.dextra.makemagic.domain.repositories.params.CharacterFilterParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {

    Page<CharacterDTO> searchAllCharacters(CharacterFilterParams param, int page, int qtd);

    CharacterDTO findCharacterById(Long characterId);

    CharacterDTO saveCharacter(CreateCharacterDTO createCharacter) throws JsonProcessingException;

    CharacterDTO updateCharacter(Long characterId, UpdatedCharacterDTO updatedCharacter);

    ResponseEntity<Void> deleteCharacter(Long characterId);
}
