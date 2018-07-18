package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PosteService;
import io.github.jhipster.application.domain.Poste;
import io.github.jhipster.application.repository.PosteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Poste.
 */
@Service
@Transactional
public class PosteServiceImpl implements PosteService {

    private final Logger log = LoggerFactory.getLogger(PosteServiceImpl.class);

    private final PosteRepository posteRepository;

    public PosteServiceImpl(PosteRepository posteRepository) {
        this.posteRepository = posteRepository;
    }

    /**
     * Save a poste.
     *
     * @param poste the entity to save
     * @return the persisted entity
     */
    @Override
    public Poste save(Poste poste) {
        log.debug("Request to save Poste : {}", poste);        return posteRepository.save(poste);
    }

    /**
     * Get all the postes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Poste> findAll() {
        log.debug("Request to get all Postes");
        return posteRepository.findAll();
    }


    /**
     * Get one poste by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Poste> findOne(Long id) {
        log.debug("Request to get Poste : {}", id);
        return posteRepository.findById(id);
    }

    /**
     * Delete the poste by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Poste : {}", id);
        posteRepository.deleteById(id);
    }
}
