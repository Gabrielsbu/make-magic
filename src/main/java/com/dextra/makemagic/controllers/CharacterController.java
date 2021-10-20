package com.dextra.makemagic.controllers;

import com.dextra.makemagic.domain.dto.CharacterDTO;
import com.dextra.makemagic.domain.dto.CreateCharacterDTO;
import com.dextra.makemagic.domain.dto.UpdatedCharacterDTO;
import com.dextra.makemagic.domain.repositories.params.CharacterFilterParams;
import com.dextra.makemagic.domain.services.CharacterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping
    public Page<CharacterDTO> getAllCharacters(CharacterFilterParams param, @RequestParam int page, @RequestParam int qtd) {
        return characterService.searchAllCharacters(param, page, qtd);
    }

    @GetMapping("/{characterId}")
    public CharacterDTO searchCharacterById(@PathVariable Long characterId) {
        return characterService.findCharacterById(characterId);
    }

    @PostMapping
    public CharacterDTO createCharacter(@RequestBody CreateCharacterDTO createCharacter) throws JsonProcessingException {
        return characterService.saveCharacter(createCharacter);
    }

    @PutMapping("/{characterId}")
    public CharacterDTO updateCharacterById(@PathVariable Long characterId, UpdatedCharacterDTO updatedCharacter) {
        return characterService.updateCharacter(characterId, updatedCharacter);
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<Void> deleteCharacterById(@PathVariable Long characterId) {
        return characterService.deleteCharacter(characterId);
    }
}