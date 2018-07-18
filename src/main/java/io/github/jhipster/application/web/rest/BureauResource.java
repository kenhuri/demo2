package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Bureau;
import io.github.jhipster.application.repository.BureauRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Bureau.
 */
@RestController
@RequestMapping("/api")
public class BureauResource {

    private final Logger log = LoggerFactory.getLogger(BureauResource.class);

    private static final String ENTITY_NAME = "bureau";

    private final BureauRepository bureauRepository;

    public BureauResource(BureauRepository bureauRepository) {
        this.bureauRepository = bureauRepository;
    }

    /**
     * POST  /bureaus : Create a new bureau.
     *
     * @param bureau the bureau to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bureau, or with status 400 (Bad Request) if the bureau has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bureaus")
    @Timed
    public ResponseEntity<Bureau> createBureau(@RequestBody Bureau bureau) throws URISyntaxException {
        log.debug("REST request to save Bureau : {}", bureau);
        if (bureau.getId() != null) {
            throw new BadRequestAlertException("A new bureau cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bureau result = bureauRepository.save(bureau);
        return ResponseEntity.created(new URI("/api/bureaus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bureaus : Updates an existing bureau.
     *
     * @param bureau the bureau to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bureau,
     * or with status 400 (Bad Request) if the bureau is not valid,
     * or with status 500 (Internal Server Error) if the bureau couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bureaus")
    @Timed
    public ResponseEntity<Bureau> updateBureau(@RequestBody Bureau bureau) throws URISyntaxException {
        log.debug("REST request to update Bureau : {}", bureau);
        if (bureau.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bureau result = bureauRepository.save(bureau);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bureau.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bureaus : get all the bureaus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bureaus in body
     */
    @GetMapping("/bureaus")
    @Timed
    public ResponseEntity<List<Bureau>> getAllBureaus(Pageable pageable) {
        log.debug("REST request to get a page of Bureaus");
        Page<Bureau> page = bureauRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bureaus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /bureaus/:id : get the "id" bureau.
     *
     * @param id the id of the bureau to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bureau, or with status 404 (Not Found)
     */
    @GetMapping("/bureaus/{id}")
    @Timed
    public ResponseEntity<Bureau> getBureau(@PathVariable Long id) {
        log.debug("REST request to get Bureau : {}", id);
        Optional<Bureau> bureau = bureauRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bureau);
    }

    /**
     * DELETE  /bureaus/:id : delete the "id" bureau.
     *
     * @param id the id of the bureau to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bureaus/{id}")
    @Timed
    public ResponseEntity<Void> deleteBureau(@PathVariable Long id) {
        log.debug("REST request to delete Bureau : {}", id);

        bureauRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
