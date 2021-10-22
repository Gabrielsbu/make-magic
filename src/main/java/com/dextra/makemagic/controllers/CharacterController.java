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

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/characters")
public class CharacterController {

    private final CharacterService characterService;

    //Método (getAllCharacters): Responsável por buscar todos os personagens no banco de dados.
    @GetMapping
    public Page<CharacterDTO> getAllCharacters(CharacterFilterParams param, @RequestParam int page, @RequestParam int qtd) {
        return characterService.searchAllCharacters(param, page, qtd);
    }
    
    //Método (searchCharacterById): Responsável por buscar apenas um personagem por ID no banco de dados.
    @GetMapping("/{characterId}")
    public CharacterDTO searchCharacterById(@PathVariable Long characterId) {
        return characterService.findCharacterById(characterId);
    }

    //Método (createCharacter): Responsável por criar um personagem no banco de dados.
    @PostMapping
    public CharacterDTO createCharacter(@RequestBody @Valid CreateCharacterDTO createCharacter) throws JsonProcessingException {
        return characterService.saveCharacter(createCharacter);
    }

    //Método (updateCharacterById): Responsável por editar um personagem no banco de dados.
    @PutMapping("/{characterId}")
    public CharacterDTO updateCharacterById(@PathVariable Long characterId, @RequestBody @Valid UpdatedCharacterDTO updatedCharacter) {
        return characterService.updateCharacter(characterId, updatedCharacter);
    }

    //Método (deleteCharacterById): Responsável por deletar por ID um personagem no banco de dados.
    @DeleteMapping("/{characterId}")
    public ResponseEntity<Void> deleteCharacterById(@PathVariable Long characterId) {
        return characterService.deleteCharacter(characterId);
    }
}