package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PosteService;
import io.github.jhipster.application.domain.Poste;
import io.github.jhipster.application.repository.PosteRepository;
import io.github.jhipster.application.service.dto.PosteDTO;
import io.github.jhipster.application.service.mapper.PosteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Poste.
 */
@Service
@Transactional
public class PosteServiceImpl implements PosteService {

    private final Logger log = LoggerFactory.getLogger(PosteServiceImpl.class);

    private final PosteRepository posteRepository;

    private final PosteMapper posteMapper;

    public PosteServiceImpl(PosteRepository posteRepository, PosteMapper posteMapper) {
        this.posteRepository = posteRepository;
        this.posteMapper = posteMapper;
    }

    /**
     * Save a poste.
     *
     * @param posteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PosteDTO save(PosteDTO posteDTO) {
        log.debug("Request to save Poste : {}", posteDTO);
        Poste poste = posteMapper.toEntity(posteDTO);
        poste = posteRepository.save(poste);
        return posteMapper.toDto(poste);
    }

    /**
     * Get all the postes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PosteDTO> findAll() {
        log.debug("Request to get all Postes");
        return posteRepository.findAll().stream()
            .map(posteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one poste by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PosteDTO> findOne(Long id) {
        log.debug("Request to get Poste : {}", id);
        return posteRepository.findById(id)
            .map(posteMapper::toDto);
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
