package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.PosteService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.PosteDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Poste.
 */
@RestController
@RequestMapping("/api")
public class PosteResource {

    private final Logger log = LoggerFactory.getLogger(PosteResource.class);

    private static final String ENTITY_NAME = "poste";

    private final PosteService posteService;

    public PosteResource(PosteService posteService) {
        this.posteService = posteService;
    }

    /**
     * POST  /postes : Create a new poste.
     *
     * @param posteDTO the posteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new posteDTO, or with status 400 (Bad Request) if the poste has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/postes")
    @Timed
    public ResponseEntity<PosteDTO> createPoste(@RequestBody PosteDTO posteDTO) throws URISyntaxException {
        log.debug("REST request to save Poste : {}", posteDTO);
        if (posteDTO.getId() != null) {
            throw new BadRequestAlertException("A new poste cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PosteDTO result = posteService.save(posteDTO);
        return ResponseEntity.created(new URI("/api/postes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /postes : Updates an existing poste.
     *
     * @param posteDTO the posteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated posteDTO,
     * or with status 400 (Bad Request) if the posteDTO is not valid,
     * or with status 500 (Internal Server Error) if the posteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/postes")
    @Timed
    public ResponseEntity<PosteDTO> updatePoste(@RequestBody PosteDTO posteDTO) throws URISyntaxException {
        log.debug("REST request to update Poste : {}", posteDTO);
        if (posteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PosteDTO result = posteService.save(posteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, posteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /postes : get all the postes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of postes in body
     */
    @GetMapping("/postes")
    @Timed
    public List<PosteDTO> getAllPostes() {
        log.debug("REST request to get all Postes");
        return posteService.findAll();
    }

    /**
     * GET  /postes/:id : get the "id" poste.
     *
     * @param id the id of the posteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the posteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/postes/{id}")
    @Timed
    public ResponseEntity<PosteDTO> getPoste(@PathVariable Long id) {
        log.debug("REST request to get Poste : {}", id);
        Optional<PosteDTO> posteDTO = posteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(posteDTO);
    }

    /**
     * DELETE  /postes/:id : delete the "id" poste.
     *
     * @param id the id of the posteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/postes/{id}")
    @Timed
    public ResponseEntity<Void> deletePoste(@PathVariable Long id) {
        log.debug("REST request to delete Poste : {}", id);
        posteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
