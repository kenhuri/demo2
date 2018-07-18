package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Poste;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Poste.
 */
public interface PosteService {

    /**
     * Save a poste.
     *
     * @param poste the entity to save
     * @return the persisted entity
     */
    Poste save(Poste poste);

    /**
     * Get all the postes.
     *
     * @return the list of entities
     */
    List<Poste> findAll();


    /**
     * Get the "id" poste.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Poste> findOne(Long id);

    /**
     * Delete the "id" poste.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
