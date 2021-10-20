package com.dextra.makemagic.domain.repositories;

import com.dextra.makemagic.domain.models.Character;
import com.dextra.makemagic.domain.repositories.params.CharacterFilterParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterRepositoryCustom {
    Page<Character> getCharacterPageable(CharacterFilterParams params, Pageable pageable);
}
