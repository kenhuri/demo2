package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.Demo2App;

import io.github.jhipster.application.domain.Bureau;
import io.github.jhipster.application.repository.BureauRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BureauResource REST controller.
 *
 * @see BureauResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo2App.class)
public class BureauResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ESPACE = "AAAAAAAAAA";
    private static final String UPDATED_ESPACE = "BBBBBBBBBB";

    @Autowired
    private BureauRepository bureauRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBureauMockMvc;

    private Bureau bureau;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BureauResource bureauResource = new BureauResource(bureauRepository);
        this.restBureauMockMvc = MockMvcBuilders.standaloneSetup(bureauResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bureau createEntity(EntityManager em) {
        Bureau bureau = new Bureau()
            .name(DEFAULT_NAME)
            .espace(DEFAULT_ESPACE);
        return bureau;
    }

    @Before
    public void initTest() {
        bureau = createEntity(em);
    }

    @Test
    @Transactional
    public void createBureau() throws Exception {
        int databaseSizeBeforeCreate = bureauRepository.findAll().size();

        // Create the Bureau
        restBureauMockMvc.perform(post("/api/bureaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bureau)))
            .andExpect(status().isCreated());

        // Validate the Bureau in the database
        List<Bureau> bureauList = bureauRepository.findAll();
        assertThat(bureauList).hasSize(databaseSizeBeforeCreate + 1);
        Bureau testBureau = bureauList.get(bureauList.size() - 1);
        assertThat(testBureau.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBureau.getEspace()).isEqualTo(DEFAULT_ESPACE);
    }

    @Test
    @Transactional
    public void createBureauWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bureauRepository.findAll().size();

        // Create the Bureau with an existing ID
        bureau.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBureauMockMvc.perform(post("/api/bureaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bureau)))
            .andExpect(status().isBadRequest());

        // Validate the Bureau in the database
        List<Bureau> bureauList = bureauRepository.findAll();
        assertThat(bureauList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBureaus() throws Exception {
        // Initialize the database
        bureauRepository.saveAndFlush(bureau);

        // Get all the bureauList
        restBureauMockMvc.perform(get("/api/bureaus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bureau.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].espace").value(hasItem(DEFAULT_ESPACE.toString())));
    }
    

    @Test
    @Transactional
    public void getBureau() throws Exception {
        // Initialize the database
        bureauRepository.saveAndFlush(bureau);

        // Get the bureau
        restBureauMockMvc.perform(get("/api/bureaus/{id}", bureau.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bureau.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.espace").value(DEFAULT_ESPACE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBureau() throws Exception {
        // Get the bureau
        restBureauMockMvc.perform(get("/api/bureaus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBureau() throws Exception {
        // Initialize the database
        bureauRepository.saveAndFlush(bureau);

        int databaseSizeBeforeUpdate = bureauRepository.findAll().size();

        // Update the bureau
        Bureau updatedBureau = bureauRepository.findById(bureau.getId()).get();
        // Disconnect from session so that the updates on updatedBureau are not directly saved in db
        em.detach(updatedBureau);
        updatedBureau
            .name(UPDATED_NAME)
            .espace(UPDATED_ESPACE);

        restBureauMockMvc.perform(put("/api/bureaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBureau)))
            .andExpect(status().isOk());

        // Validate the Bureau in the database
        List<Bureau> bureauList = bureauRepository.findAll();
        assertThat(bureauList).hasSize(databaseSizeBeforeUpdate);
        Bureau testBureau = bureauList.get(bureauList.size() - 1);
        assertThat(testBureau.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBureau.getEspace()).isEqualTo(UPDATED_ESPACE);
    }

    @Test
    @Transactional
    public void updateNonExistingBureau() throws Exception {
        int databaseSizeBeforeUpdate = bureauRepository.findAll().size();

        // Create the Bureau

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBureauMockMvc.perform(put("/api/bureaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bureau)))
            .andExpect(status().isBadRequest());

        // Validate the Bureau in the database
        List<Bureau> bureauList = bureauRepository.findAll();
        assertThat(bureauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBureau() throws Exception {
        // Initialize the database
        bureauRepository.saveAndFlush(bureau);

        int databaseSizeBeforeDelete = bureauRepository.findAll().size();

        // Get the bureau
        restBureauMockMvc.perform(delete("/api/bureaus/{id}", bureau.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bureau> bureauList = bureauRepository.findAll();
        assertThat(bureauList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bureau.class);
        Bureau bureau1 = new Bureau();
        bureau1.setId(1L);
        Bureau bureau2 = new Bureau();
        bureau2.setId(bureau1.getId());
        assertThat(bureau1).isEqualTo(bureau2);
        bureau2.setId(2L);
        assertThat(bureau1).isNotEqualTo(bureau2);
        bureau1.setId(null);
        assertThat(bureau1).isNotEqualTo(bureau2);
    }
}
