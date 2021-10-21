package com.dextra.makemagic.domain.services.Impl;

import com.dextra.makemagic.domain.dto.CharacterDTO;
import com.dextra.makemagic.domain.dto.CreateCharacterDTO;
import com.dextra.makemagic.domain.dto.UpdatedCharacterDTO;
import com.dextra.makemagic.domain.models.Character;
import com.dextra.makemagic.domain.repositories.CharacterRepository;
import com.dextra.makemagic.domain.repositories.params.CharacterFilterParams;
import com.dextra.makemagic.domain.services.CharacterService;
import com.dextra.makemagic.exceptions.AllException;
import com.dextra.makemagic.utils.CharacterConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterConverter characterConverter;

    @Value("api.key")
    private String apiKey;

    //Método(getAllProfilePagination): responsável por buscar todos os personagens de forma paginada, método auxiliar para o searchAllCharacters
    private Page<CharacterDTO> getAllProfilePagination(CharacterFilterParams param, Pageable pageable) {
        Page<Character> pageAuth = characterRepository.getCharacterPageable(param, pageable);
        return pageAuth.map(characterConverter::toDTO);
    }

    //Método(searchAllCharacters): responsável por buscar todos os personagens.
    @Override
    public Page<CharacterDTO> searchAllCharacters(CharacterFilterParams param, int page, int qtd) {
        Pageable pageable = PageRequest.of(page, qtd);

        Page<CharacterDTO> result = getAllProfilePagination(param, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.add("elements", Long.toString(result.getTotalElements()));
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "elements");

        return result;
    }

    //Método(findCharacterById): responsável por buscar personagem por id.
    @Override
    public CharacterDTO findCharacterById(Long characterId) {
        return characterConverter.toDTO(characterRepository.findById(characterId)
                .orElseThrow(() -> new AllException("Character not found", HttpStatus.NOT_FOUND)));
    }

    //Método(saveCharacter): responsável por salvar personagem.
    @Override
    public CharacterDTO saveCharacter(CreateCharacterDTO createCharacter) {
        if(createCharacter.getHouse() != null) {
            boolean existHouse = findHouse(createCharacter.getHouse());

            if(!existHouse) {
                throw new AllException("House not found", HttpStatus.NOT_FOUND);
            }
        }

        Character character = Character.builder()
                .setName(createCharacter.getName())
                .setRole(createCharacter.getRole())
                .setSchool(createCharacter.getSchool())
                .setHouse(createCharacter.getHouse())
                .setPatronus(createCharacter.getPatronus())
                .build();

        return characterConverter.toDTO(characterRepository.save(character));
    }

    //Método(findHouse): responsável por buscar todas as casas de PotterAPI informando a key cadastrada e retorna true ou false caso exista o nome informado pelo
    // usuário no momento de cadastrar um personagem.
    private boolean findHouse(String houseName){

        RestTemplate restTemplate = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance().scheme("http").host("us-central1-rh-challenges.cloudfunctions.net")
                .path("/potterApi/houses").queryParam("fields", "all").build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("apiKey", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(uri.toString(), headers);

        String jsonHouses = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class).toString();

        return jsonHouses.contains(houseName);
    }

    //Método(updateCharacter): responsável por atualizar um personagem
    @Override
    public CharacterDTO updateCharacter(Long characterId, UpdatedCharacterDTO updatedCharacter) {

        Character characterExistent = characterConverter
                .toModel(findCharacterById(characterId));

        if(updatedCharacter.getHouse() != null) {
            boolean existHouse = findHouse(updatedCharacter.getHouse());

            if(!existHouse) {
                throw new AllException("House not found", HttpStatus.NOT_FOUND);
            }

            characterExistent.setHouse(updatedCharacter.getHouse());
        }

        if(updatedCharacter.getName() != null) {
            characterExistent.setName(updatedCharacter.getName());

        }

        if(updatedCharacter.getRole() != null) {
            characterExistent.setRole(updatedCharacter.getRole());

        }

        if(updatedCharacter.getSchool() != null) {
            characterExistent.setRole(updatedCharacter.getSchool());

        }

        if(updatedCharacter.getHouse() != null) {
            characterExistent.setRole(updatedCharacter.getHouse());

        }

        if(updatedCharacter.getPatronus() != null) {
            characterExistent.setPatronus(updatedCharacter.getPatronus());

        }

        return characterConverter.toDTO(characterRepository.save(characterExistent));
    }

    //Método(deleteCharacter): responsável por deletar um personagem
    @Override
    public ResponseEntity<Void> deleteCharacter(Long characterId) {
        Optional<Character> character = characterRepository.findById(characterId);

        if (character.isEmpty()) {
            throw new AllException("Character not found", HttpStatus.NOT_FOUND);
        }

        characterRepository.deleteById(characterId);

        return ResponseEntity.noContent().build();

    }
}
