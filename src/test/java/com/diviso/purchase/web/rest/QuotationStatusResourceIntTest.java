package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.QuotationStatus;
import com.diviso.purchase.repository.QuotationStatusRepository;
import com.diviso.purchase.service.QuotationStatusService;
import com.diviso.purchase.service.dto.QuotationStatusDTO;
import com.diviso.purchase.service.mapper.QuotationStatusMapper;
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
 * Test class for the QuotationStatusResource REST controller.
 *
 * @see QuotationStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class QuotationStatusResourceIntTest {

    private static final Integer DEFAULT_STATUS_ID = 1;
    private static final Integer UPDATED_STATUS_ID = 2;

    private static final String DEFAULT_STATUS_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_LEVEL = "BBBBBBBBBB";

    @Autowired
    private QuotationStatusRepository quotationStatusRepository;

    @Autowired
    private QuotationStatusMapper quotationStatusMapper;

    @Autowired
    private QuotationStatusService quotationStatusService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuotationStatusMockMvc;

    private QuotationStatus quotationStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuotationStatusResource quotationStatusResource = new QuotationStatusResource(quotationStatusService);
        this.restQuotationStatusMockMvc = MockMvcBuilders.standaloneSetup(quotationStatusResource)
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
    public static QuotationStatus createEntity(EntityManager em) {
        QuotationStatus quotationStatus = new QuotationStatus()
            .statusId(DEFAULT_STATUS_ID)
            .statusLevel(DEFAULT_STATUS_LEVEL);
        return quotationStatus;
    }

    @Before
    public void initTest() {
        quotationStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuotationStatus() throws Exception {
        int databaseSizeBeforeCreate = quotationStatusRepository.findAll().size();

        // Create the QuotationStatus
        QuotationStatusDTO quotationStatusDTO = quotationStatusMapper.toDto(quotationStatus);
        restQuotationStatusMockMvc.perform(post("/api/quotation-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the QuotationStatus in the database
        List<QuotationStatus> quotationStatusList = quotationStatusRepository.findAll();
        assertThat(quotationStatusList).hasSize(databaseSizeBeforeCreate + 1);
        QuotationStatus testQuotationStatus = quotationStatusList.get(quotationStatusList.size() - 1);
        assertThat(testQuotationStatus.getStatusId()).isEqualTo(DEFAULT_STATUS_ID);
        assertThat(testQuotationStatus.getStatusLevel()).isEqualTo(DEFAULT_STATUS_LEVEL);
    }

    @Test
    @Transactional
    public void createQuotationStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quotationStatusRepository.findAll().size();

        // Create the QuotationStatus with an existing ID
        quotationStatus.setId(1L);
        QuotationStatusDTO quotationStatusDTO = quotationStatusMapper.toDto(quotationStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuotationStatusMockMvc.perform(post("/api/quotation-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuotationStatus in the database
        List<QuotationStatus> quotationStatusList = quotationStatusRepository.findAll();
        assertThat(quotationStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuotationStatuses() throws Exception {
        // Initialize the database
        quotationStatusRepository.saveAndFlush(quotationStatus);

        // Get all the quotationStatusList
        restQuotationStatusMockMvc.perform(get("/api/quotation-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quotationStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].statusId").value(hasItem(DEFAULT_STATUS_ID)))
            .andExpect(jsonPath("$.[*].statusLevel").value(hasItem(DEFAULT_STATUS_LEVEL.toString())));
    }

    @Test
    @Transactional
    public void getQuotationStatus() throws Exception {
        // Initialize the database
        quotationStatusRepository.saveAndFlush(quotationStatus);

        // Get the quotationStatus
        restQuotationStatusMockMvc.perform(get("/api/quotation-statuses/{id}", quotationStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quotationStatus.getId().intValue()))
            .andExpect(jsonPath("$.statusId").value(DEFAULT_STATUS_ID))
            .andExpect(jsonPath("$.statusLevel").value(DEFAULT_STATUS_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuotationStatus() throws Exception {
        // Get the quotationStatus
        restQuotationStatusMockMvc.perform(get("/api/quotation-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuotationStatus() throws Exception {
        // Initialize the database
        quotationStatusRepository.saveAndFlush(quotationStatus);
        int databaseSizeBeforeUpdate = quotationStatusRepository.findAll().size();

        // Update the quotationStatus
        QuotationStatus updatedQuotationStatus = quotationStatusRepository.findOne(quotationStatus.getId());
        // Disconnect from session so that the updates on updatedQuotationStatus are not directly saved in db
        em.detach(updatedQuotationStatus);
        updatedQuotationStatus
            .statusId(UPDATED_STATUS_ID)
            .statusLevel(UPDATED_STATUS_LEVEL);
        QuotationStatusDTO quotationStatusDTO = quotationStatusMapper.toDto(updatedQuotationStatus);

        restQuotationStatusMockMvc.perform(put("/api/quotation-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationStatusDTO)))
            .andExpect(status().isOk());

        // Validate the QuotationStatus in the database
        List<QuotationStatus> quotationStatusList = quotationStatusRepository.findAll();
        assertThat(quotationStatusList).hasSize(databaseSizeBeforeUpdate);
        QuotationStatus testQuotationStatus = quotationStatusList.get(quotationStatusList.size() - 1);
        assertThat(testQuotationStatus.getStatusId()).isEqualTo(UPDATED_STATUS_ID);
        assertThat(testQuotationStatus.getStatusLevel()).isEqualTo(UPDATED_STATUS_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingQuotationStatus() throws Exception {
        int databaseSizeBeforeUpdate = quotationStatusRepository.findAll().size();

        // Create the QuotationStatus
        QuotationStatusDTO quotationStatusDTO = quotationStatusMapper.toDto(quotationStatus);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQuotationStatusMockMvc.perform(put("/api/quotation-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the QuotationStatus in the database
        List<QuotationStatus> quotationStatusList = quotationStatusRepository.findAll();
        assertThat(quotationStatusList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQuotationStatus() throws Exception {
        // Initialize the database
        quotationStatusRepository.saveAndFlush(quotationStatus);
        int databaseSizeBeforeDelete = quotationStatusRepository.findAll().size();

        // Get the quotationStatus
        restQuotationStatusMockMvc.perform(delete("/api/quotation-statuses/{id}", quotationStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuotationStatus> quotationStatusList = quotationStatusRepository.findAll();
        assertThat(quotationStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotationStatus.class);
        QuotationStatus quotationStatus1 = new QuotationStatus();
        quotationStatus1.setId(1L);
        QuotationStatus quotationStatus2 = new QuotationStatus();
        quotationStatus2.setId(quotationStatus1.getId());
        assertThat(quotationStatus1).isEqualTo(quotationStatus2);
        quotationStatus2.setId(2L);
        assertThat(quotationStatus1).isNotEqualTo(quotationStatus2);
        quotationStatus1.setId(null);
        assertThat(quotationStatus1).isNotEqualTo(quotationStatus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotationStatusDTO.class);
        QuotationStatusDTO quotationStatusDTO1 = new QuotationStatusDTO();
        quotationStatusDTO1.setId(1L);
        QuotationStatusDTO quotationStatusDTO2 = new QuotationStatusDTO();
        assertThat(quotationStatusDTO1).isNotEqualTo(quotationStatusDTO2);
        quotationStatusDTO2.setId(quotationStatusDTO1.getId());
        assertThat(quotationStatusDTO1).isEqualTo(quotationStatusDTO2);
        quotationStatusDTO2.setId(2L);
        assertThat(quotationStatusDTO1).isNotEqualTo(quotationStatusDTO2);
        quotationStatusDTO1.setId(null);
        assertThat(quotationStatusDTO1).isNotEqualTo(quotationStatusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quotationStatusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quotationStatusMapper.fromId(null)).isNull();
    }
}
