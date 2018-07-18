package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Poste;
import io.github.jhipster.application.service.PosteService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
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
     * @param poste the poste to create
     * @return the ResponseEntity with status 201 (Created) and with body the new poste, or with status 400 (Bad Request) if the poste has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/postes")
    @Timed
    public ResponseEntity<Poste> createPoste(@RequestBody Poste poste) throws URISyntaxException {
        log.debug("REST request to save Poste : {}", poste);
        if (poste.getId() != null) {
            throw new BadRequestAlertException("A new poste cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Poste result = posteService.save(poste);
        return ResponseEntity.created(new URI("/api/postes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /postes : Updates an existing poste.
     *
     * @param poste the poste to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated poste,
     * or with status 400 (Bad Request) if the poste is not valid,
     * or with status 500 (Internal Server Error) if the poste couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/postes")
    @Timed
    public ResponseEntity<Poste> updatePoste(@RequestBody Poste poste) throws URISyntaxException {
        log.debug("REST request to update Poste : {}", poste);
        if (poste.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Poste result = posteService.save(poste);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, poste.getId().toString()))
            .body(result);
    }

    /**
     * GET  /postes : get all the postes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of postes in body
     */
    @GetMapping("/postes")
    @Timed
    public List<Poste> getAllPostes() {
        log.debug("REST request to get all Postes");
        return posteService.findAll();
    }

    /**
     * GET  /postes/:id : get the "id" poste.
     *
     * @param id the id of the poste to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the poste, or with status 404 (Not Found)
     */
    @GetMapping("/postes/{id}")
    @Timed
    public ResponseEntity<Poste> getPoste(@PathVariable Long id) {
        log.debug("REST request to get Poste : {}", id);
        Optional<Poste> poste = posteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(poste);
    }

    /**
     * DELETE  /postes/:id : delete the "id" poste.
     *
     * @param id the id of the poste to delete
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
