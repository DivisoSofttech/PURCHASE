package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.Alert;
import com.diviso.purchase.repository.AlertRepository;
import com.diviso.purchase.service.AlertService;
import com.diviso.purchase.service.dto.AlertDTO;
import com.diviso.purchase.service.mapper.AlertMapper;
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
 * Test class for the AlertResource REST controller.
 *
 * @see AlertResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class AlertResourceIntTest {

    private static final String DEFAULT_PERCENTAGE = "AAAAAAAAAA";
    private static final String UPDATED_PERCENTAGE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    private AlertService alertService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlertMockMvc;

    private Alert alert;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlertResource alertResource = new AlertResource(alertService);
        this.restAlertMockMvc = MockMvcBuilders.standaloneSetup(alertResource)
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
    public static Alert createEntity(EntityManager em) {
        Alert alert = new Alert()
            .percentage(DEFAULT_PERCENTAGE)
            .price(DEFAULT_PRICE);
        return alert;
    }

    @Before
    public void initTest() {
        alert = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlert() throws Exception {
        int databaseSizeBeforeCreate = alertRepository.findAll().size();

        // Create the Alert
        AlertDTO alertDTO = alertMapper.toDto(alert);
        restAlertMockMvc.perform(post("/api/alerts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alertDTO)))
            .andExpect(status().isCreated());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeCreate + 1);
        Alert testAlert = alertList.get(alertList.size() - 1);
        assertThat(testAlert.getPercentage()).isEqualTo(DEFAULT_PERCENTAGE);
        assertThat(testAlert.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createAlertWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alertRepository.findAll().size();

        // Create the Alert with an existing ID
        alert.setId(1L);
        AlertDTO alertDTO = alertMapper.toDto(alert);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlertMockMvc.perform(post("/api/alerts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alertDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAlerts() throws Exception {
        // Initialize the database
        alertRepository.saveAndFlush(alert);

        // Get all the alertList
        restAlertMockMvc.perform(get("/api/alerts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alert.getId().intValue())))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(DEFAULT_PERCENTAGE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    public void getAlert() throws Exception {
        // Initialize the database
        alertRepository.saveAndFlush(alert);

        // Get the alert
        restAlertMockMvc.perform(get("/api/alerts/{id}", alert.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alert.getId().intValue()))
            .andExpect(jsonPath("$.percentage").value(DEFAULT_PERCENTAGE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAlert() throws Exception {
        // Get the alert
        restAlertMockMvc.perform(get("/api/alerts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlert() throws Exception {
        // Initialize the database
        alertRepository.saveAndFlush(alert);
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();

        // Update the alert
        Alert updatedAlert = alertRepository.findOne(alert.getId());
        // Disconnect from session so that the updates on updatedAlert are not directly saved in db
        em.detach(updatedAlert);
        updatedAlert
            .percentage(UPDATED_PERCENTAGE)
            .price(UPDATED_PRICE);
        AlertDTO alertDTO = alertMapper.toDto(updatedAlert);

        restAlertMockMvc.perform(put("/api/alerts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alertDTO)))
            .andExpect(status().isOk());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
        Alert testAlert = alertList.get(alertList.size() - 1);
        assertThat(testAlert.getPercentage()).isEqualTo(UPDATED_PERCENTAGE);
        assertThat(testAlert.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingAlert() throws Exception {
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();

        // Create the Alert
        AlertDTO alertDTO = alertMapper.toDto(alert);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlertMockMvc.perform(put("/api/alerts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alertDTO)))
            .andExpect(status().isCreated());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlert() throws Exception {
        // Initialize the database
        alertRepository.saveAndFlush(alert);
        int databaseSizeBeforeDelete = alertRepository.findAll().size();

        // Get the alert
        restAlertMockMvc.perform(delete("/api/alerts/{id}", alert.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alert.class);
        Alert alert1 = new Alert();
        alert1.setId(1L);
        Alert alert2 = new Alert();
        alert2.setId(alert1.getId());
        assertThat(alert1).isEqualTo(alert2);
        alert2.setId(2L);
        assertThat(alert1).isNotEqualTo(alert2);
        alert1.setId(null);
        assertThat(alert1).isNotEqualTo(alert2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlertDTO.class);
        AlertDTO alertDTO1 = new AlertDTO();
        alertDTO1.setId(1L);
        AlertDTO alertDTO2 = new AlertDTO();
        assertThat(alertDTO1).isNotEqualTo(alertDTO2);
        alertDTO2.setId(alertDTO1.getId());
        assertThat(alertDTO1).isEqualTo(alertDTO2);
        alertDTO2.setId(2L);
        assertThat(alertDTO1).isNotEqualTo(alertDTO2);
        alertDTO1.setId(null);
        assertThat(alertDTO1).isNotEqualTo(alertDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(alertMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(alertMapper.fromId(null)).isNull();
    }
}
