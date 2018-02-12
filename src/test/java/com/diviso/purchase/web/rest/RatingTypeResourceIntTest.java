package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.RatingType;
import com.diviso.purchase.repository.RatingTypeRepository;
import com.diviso.purchase.service.RatingTypeService;
import com.diviso.purchase.service.dto.RatingTypeDTO;
import com.diviso.purchase.service.mapper.RatingTypeMapper;
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
 * Test class for the RatingTypeResource REST controller.
 *
 * @see RatingTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class RatingTypeResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private RatingTypeRepository ratingTypeRepository;

    @Autowired
    private RatingTypeMapper ratingTypeMapper;

    @Autowired
    private RatingTypeService ratingTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRatingTypeMockMvc;

    private RatingType ratingType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RatingTypeResource ratingTypeResource = new RatingTypeResource(ratingTypeService);
        this.restRatingTypeMockMvc = MockMvcBuilders.standaloneSetup(ratingTypeResource)
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
    public static RatingType createEntity(EntityManager em) {
        RatingType ratingType = new RatingType()
            .reference(DEFAULT_REFERENCE)
            .type(DEFAULT_TYPE);
        return ratingType;
    }

    @Before
    public void initTest() {
        ratingType = createEntity(em);
    }

    @Test
    @Transactional
    public void createRatingType() throws Exception {
        int databaseSizeBeforeCreate = ratingTypeRepository.findAll().size();

        // Create the RatingType
        RatingTypeDTO ratingTypeDTO = ratingTypeMapper.toDto(ratingType);
        restRatingTypeMockMvc.perform(post("/api/rating-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the RatingType in the database
        List<RatingType> ratingTypeList = ratingTypeRepository.findAll();
        assertThat(ratingTypeList).hasSize(databaseSizeBeforeCreate + 1);
        RatingType testRatingType = ratingTypeList.get(ratingTypeList.size() - 1);
        assertThat(testRatingType.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testRatingType.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createRatingTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ratingTypeRepository.findAll().size();

        // Create the RatingType with an existing ID
        ratingType.setId(1L);
        RatingTypeDTO ratingTypeDTO = ratingTypeMapper.toDto(ratingType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRatingTypeMockMvc.perform(post("/api/rating-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RatingType in the database
        List<RatingType> ratingTypeList = ratingTypeRepository.findAll();
        assertThat(ratingTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRatingTypes() throws Exception {
        // Initialize the database
        ratingTypeRepository.saveAndFlush(ratingType);

        // Get all the ratingTypeList
        restRatingTypeMockMvc.perform(get("/api/rating-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ratingType.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getRatingType() throws Exception {
        // Initialize the database
        ratingTypeRepository.saveAndFlush(ratingType);

        // Get the ratingType
        restRatingTypeMockMvc.perform(get("/api/rating-types/{id}", ratingType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ratingType.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRatingType() throws Exception {
        // Get the ratingType
        restRatingTypeMockMvc.perform(get("/api/rating-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRatingType() throws Exception {
        // Initialize the database
        ratingTypeRepository.saveAndFlush(ratingType);
        int databaseSizeBeforeUpdate = ratingTypeRepository.findAll().size();

        // Update the ratingType
        RatingType updatedRatingType = ratingTypeRepository.findOne(ratingType.getId());
        // Disconnect from session so that the updates on updatedRatingType are not directly saved in db
        em.detach(updatedRatingType);
        updatedRatingType
            .reference(UPDATED_REFERENCE)
            .type(UPDATED_TYPE);
        RatingTypeDTO ratingTypeDTO = ratingTypeMapper.toDto(updatedRatingType);

        restRatingTypeMockMvc.perform(put("/api/rating-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingTypeDTO)))
            .andExpect(status().isOk());

        // Validate the RatingType in the database
        List<RatingType> ratingTypeList = ratingTypeRepository.findAll();
        assertThat(ratingTypeList).hasSize(databaseSizeBeforeUpdate);
        RatingType testRatingType = ratingTypeList.get(ratingTypeList.size() - 1);
        assertThat(testRatingType.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testRatingType.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingRatingType() throws Exception {
        int databaseSizeBeforeUpdate = ratingTypeRepository.findAll().size();

        // Create the RatingType
        RatingTypeDTO ratingTypeDTO = ratingTypeMapper.toDto(ratingType);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRatingTypeMockMvc.perform(put("/api/rating-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the RatingType in the database
        List<RatingType> ratingTypeList = ratingTypeRepository.findAll();
        assertThat(ratingTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRatingType() throws Exception {
        // Initialize the database
        ratingTypeRepository.saveAndFlush(ratingType);
        int databaseSizeBeforeDelete = ratingTypeRepository.findAll().size();

        // Get the ratingType
        restRatingTypeMockMvc.perform(delete("/api/rating-types/{id}", ratingType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RatingType> ratingTypeList = ratingTypeRepository.findAll();
        assertThat(ratingTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingType.class);
        RatingType ratingType1 = new RatingType();
        ratingType1.setId(1L);
        RatingType ratingType2 = new RatingType();
        ratingType2.setId(ratingType1.getId());
        assertThat(ratingType1).isEqualTo(ratingType2);
        ratingType2.setId(2L);
        assertThat(ratingType1).isNotEqualTo(ratingType2);
        ratingType1.setId(null);
        assertThat(ratingType1).isNotEqualTo(ratingType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingTypeDTO.class);
        RatingTypeDTO ratingTypeDTO1 = new RatingTypeDTO();
        ratingTypeDTO1.setId(1L);
        RatingTypeDTO ratingTypeDTO2 = new RatingTypeDTO();
        assertThat(ratingTypeDTO1).isNotEqualTo(ratingTypeDTO2);
        ratingTypeDTO2.setId(ratingTypeDTO1.getId());
        assertThat(ratingTypeDTO1).isEqualTo(ratingTypeDTO2);
        ratingTypeDTO2.setId(2L);
        assertThat(ratingTypeDTO1).isNotEqualTo(ratingTypeDTO2);
        ratingTypeDTO1.setId(null);
        assertThat(ratingTypeDTO1).isNotEqualTo(ratingTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ratingTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ratingTypeMapper.fromId(null)).isNull();
    }
}
