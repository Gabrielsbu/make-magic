package com.dextra.makemagic.domain.repositories.impl;

import com.dextra.makemagic.domain.models.Character;
import com.dextra.makemagic.domain.models.Character_;
import com.dextra.makemagic.domain.repositories.CharacterRepositoryCustom;
import com.dextra.makemagic.domain.repositories.params.CharacterFilterParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CharacterRepositoryCustomImpl implements CharacterRepositoryCustom {

    private final EntityManager entityManager;

    //Criando a lista dinâmica de predicados
    private List<Predicate> createPredicateList(CharacterFilterParams params, CriteriaBuilder criteriaBuilder,
                                                Root<Character> root) {
        Path<String> name = root.get("name");
        Path<String> school = root.get("school");
        Path<String> role = root.get("role");
        Path<String> house = root.get("house");
        Path<String> patronus = root.get("patronus");

        List<Predicate> predicates = new ArrayList<>();

        if (params.getName() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(name), "%" + params.getName().toLowerCase() + "%"));
        }

        if (params.getSchool() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(school), "%" + params.getSchool().toLowerCase() + "%"));
        }

        if (params.getRole() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(role), "%" + params.getRole().toLowerCase() + "%"));
        }

        if (params.getHouse() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(house), "%" + params.getHouse().toLowerCase() + "%"));
        }

        if (params.getHouse() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(house), "%" + params.getHouse().toLowerCase() + "%"));
        }

        if (params.getPatronus() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(patronus), "%" + params.getPatronus().toLowerCase() + "%"));
        }

        return predicates;
    }

    //Método responsável por fazer a filtragem através dos predicados inseridos no criteria builder.
    @Override
    public Page<Character> getCharacterPageable(CharacterFilterParams param, Pageable pagination) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Character> query = criteriaBuilder.createQuery(Character.class);
        Root<Character> root = query.from(Character.class);

        List<Predicate> predicates = this.createPredicateList(param, criteriaBuilder, root);

        //Ordena a busca no formato de uma pilha, onde o último a ser inserido, vai ser o primeiro na lista.
        query.orderBy(criteriaBuilder.desc(root.get(Character_.createdCharacterAt)));

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));

        }

        TypedQuery<Character> typedQuery = entityManager.createQuery(query);

        typedQuery.setFirstResult(pagination.getPageNumber() * pagination.getPageSize());

        typedQuery.setMaxResults(pagination.getPageSize());

        List<Character> result = typedQuery.getResultList();

        return new PageImpl<>(result, pagination, this.countElements(predicates));
    }

    //apenas conta a quantidade de elemtos dentro do meu método de busca de personagens.
    private Long countElements(List<Predicate> predicates) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        query.select(criteriaBuilder.count(query.from(Character.class)));

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        return entityManager.createQuery(query).getSingleResult();
    }
}
