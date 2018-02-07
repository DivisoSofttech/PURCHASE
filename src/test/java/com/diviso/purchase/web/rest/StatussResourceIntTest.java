package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.Statuss;
import com.diviso.purchase.repository.StatussRepository;
import com.diviso.purchase.service.StatussService;
import com.diviso.purchase.service.dto.StatussDTO;
import com.diviso.purchase.service.mapper.StatussMapper;
import com.diviso.purchase.web.rest.errors.ExceptionTranslator;

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

import static com.diviso.purchase.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StatussResource REST controller.
 *
 * @see StatussResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class StatussResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_LEVEL = "BBBBBBBBBB";

    @Autowired
    private StatussRepository statussRepository;

    @Autowired
    private StatussMapper statussMapper;

    @Autowired
    private StatussService statussService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStatussMockMvc;

    private Statuss statuss;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatussResource statussResource = new StatussResource(statussService);
        this.restStatussMockMvc = MockMvcBuilders.standaloneSetup(statussResource)
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
    public static Statuss createEntity(EntityManager em) {
        Statuss statuss = new Statuss()
            .name(DEFAULT_NAME)
            .statusLevel(DEFAULT_STATUS_LEVEL);
        return statuss;
    }

    @Before
    public void initTest() {
        statuss = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatuss() throws Exception {
        int databaseSizeBeforeCreate = statussRepository.findAll().size();

        // Create the Statuss
        StatussDTO statussDTO = statussMapper.toDto(statuss);
        restStatussMockMvc.perform(post("/api/statusses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statussDTO)))
            .andExpect(status().isCreated());

        // Validate the Statuss in the database
        List<Statuss> statussList = statussRepository.findAll();
        assertThat(statussList).hasSize(databaseSizeBeforeCreate + 1);
        Statuss testStatuss = statussList.get(statussList.size() - 1);
        assertThat(testStatuss.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStatuss.getStatusLevel()).isEqualTo(DEFAULT_STATUS_LEVEL);
    }

    @Test
    @Transactional
    public void createStatussWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statussRepository.findAll().size();

        // Create the Statuss with an existing ID
        statuss.setId(1L);
        StatussDTO statussDTO = statussMapper.toDto(statuss);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatussMockMvc.perform(post("/api/statusses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statussDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Statuss in the database
        List<Statuss> statussList = statussRepository.findAll();
        assertThat(statussList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStatusses() throws Exception {
        // Initialize the database
        statussRepository.saveAndFlush(statuss);

        // Get all the statussList
        restStatussMockMvc.perform(get("/api/statusses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statuss.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].statusLevel").value(hasItem(DEFAULT_STATUS_LEVEL.toString())));
    }

    @Test
    @Transactional
    public void getStatuss() throws Exception {
        // Initialize the database
        statussRepository.saveAndFlush(statuss);

        // Get the statuss
        restStatussMockMvc.perform(get("/api/statusses/{id}", statuss.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statuss.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.statusLevel").value(DEFAULT_STATUS_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatuss() throws Exception {
        // Get the statuss
        restStatussMockMvc.perform(get("/api/statusses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatuss() throws Exception {
        // Initialize the database
        statussRepository.saveAndFlush(statuss);
        int databaseSizeBeforeUpdate = statussRepository.findAll().size();

        // Update the statuss
        Statuss updatedStatuss = statussRepository.findOne(statuss.getId());
        // Disconnect from session so that the updates on updatedStatuss are not directly saved in db
        em.detach(updatedStatuss);
        updatedStatuss
            .name(UPDATED_NAME)
            .statusLevel(UPDATED_STATUS_LEVEL);
        StatussDTO statussDTO = statussMapper.toDto(updatedStatuss);

        restStatussMockMvc.perform(put("/api/statusses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statussDTO)))
            .andExpect(status().isOk());

        // Validate the Statuss in the database
        List<Statuss> statussList = statussRepository.findAll();
        assertThat(statussList).hasSize(databaseSizeBeforeUpdate);
        Statuss testStatuss = statussList.get(statussList.size() - 1);
        assertThat(testStatuss.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStatuss.getStatusLevel()).isEqualTo(UPDATED_STATUS_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingStatuss() throws Exception {
        int databaseSizeBeforeUpdate = statussRepository.findAll().size();

        // Create the Statuss
        StatussDTO statussDTO = statussMapper.toDto(statuss);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStatussMockMvc.perform(put("/api/statusses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statussDTO)))
            .andExpect(status().isCreated());

        // Validate the Statuss in the database
        List<Statuss> statussList = statussRepository.findAll();
        assertThat(statussList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStatuss() throws Exception {
        // Initialize the database
        statussRepository.saveAndFlush(statuss);
        int databaseSizeBeforeDelete = statussRepository.findAll().size();

        // Get the statuss
        restStatussMockMvc.perform(delete("/api/statusses/{id}", statuss.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Statuss> statussList = statussRepository.findAll();
        assertThat(statussList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Statuss.class);
        Statuss statuss1 = new Statuss();
        statuss1.setId(1L);
        Statuss statuss2 = new Statuss();
        statuss2.setId(statuss1.getId());
        assertThat(statuss1).isEqualTo(statuss2);
        statuss2.setId(2L);
        assertThat(statuss1).isNotEqualTo(statuss2);
        statuss1.setId(null);
        assertThat(statuss1).isNotEqualTo(statuss2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatussDTO.class);
        StatussDTO statussDTO1 = new StatussDTO();
        statussDTO1.setId(1L);
        StatussDTO statussDTO2 = new StatussDTO();
        assertThat(statussDTO1).isNotEqualTo(statussDTO2);
        statussDTO2.setId(statussDTO1.getId());
        assertThat(statussDTO1).isEqualTo(statussDTO2);
        statussDTO2.setId(2L);
        assertThat(statussDTO1).isNotEqualTo(statussDTO2);
        statussDTO1.setId(null);
        assertThat(statussDTO1).isNotEqualTo(statussDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(statussMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(statussMapper.fromId(null)).isNull();
    }
}
