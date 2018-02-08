package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.DeliveriesLine;
import com.diviso.purchase.repository.DeliveriesLineRepository;
import com.diviso.purchase.service.DeliveriesLineService;
import com.diviso.purchase.service.dto.DeliveriesLineDTO;
import com.diviso.purchase.service.mapper.DeliveriesLineMapper;
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
 * Test class for the DeliveriesLineResource REST controller.
 *
 * @see DeliveriesLineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class DeliveriesLineResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_TAX = 1D;
    private static final Double UPDATED_TAX = 2D;

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    @Autowired
    private DeliveriesLineRepository deliveriesLineRepository;

    @Autowired
    private DeliveriesLineMapper deliveriesLineMapper;

    @Autowired
    private DeliveriesLineService deliveriesLineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDeliveriesLineMockMvc;

    private DeliveriesLine deliveriesLine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeliveriesLineResource deliveriesLineResource = new DeliveriesLineResource(deliveriesLineService);
        this.restDeliveriesLineMockMvc = MockMvcBuilders.standaloneSetup(deliveriesLineResource)
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
    public static DeliveriesLine createEntity(EntityManager em) {
        DeliveriesLine deliveriesLine = new DeliveriesLine()
            .reference(DEFAULT_REFERENCE)
            .price(DEFAULT_PRICE)
            .tax(DEFAULT_TAX)
            .quantity(DEFAULT_QUANTITY);
        return deliveriesLine;
    }

    @Before
    public void initTest() {
        deliveriesLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeliveriesLine() throws Exception {
        int databaseSizeBeforeCreate = deliveriesLineRepository.findAll().size();

        // Create the DeliveriesLine
        DeliveriesLineDTO deliveriesLineDTO = deliveriesLineMapper.toDto(deliveriesLine);
        restDeliveriesLineMockMvc.perform(post("/api/deliveries-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveriesLineDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveriesLine in the database
        List<DeliveriesLine> deliveriesLineList = deliveriesLineRepository.findAll();
        assertThat(deliveriesLineList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveriesLine testDeliveriesLine = deliveriesLineList.get(deliveriesLineList.size() - 1);
        assertThat(testDeliveriesLine.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testDeliveriesLine.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testDeliveriesLine.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testDeliveriesLine.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createDeliveriesLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveriesLineRepository.findAll().size();

        // Create the DeliveriesLine with an existing ID
        deliveriesLine.setId(1L);
        DeliveriesLineDTO deliveriesLineDTO = deliveriesLineMapper.toDto(deliveriesLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveriesLineMockMvc.perform(post("/api/deliveries-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveriesLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveriesLine in the database
        List<DeliveriesLine> deliveriesLineList = deliveriesLineRepository.findAll();
        assertThat(deliveriesLineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeliveriesLines() throws Exception {
        // Initialize the database
        deliveriesLineRepository.saveAndFlush(deliveriesLine);

        // Get all the deliveriesLineList
        restDeliveriesLineMockMvc.perform(get("/api/deliveries-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveriesLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())));
    }

    @Test
    @Transactional
    public void getDeliveriesLine() throws Exception {
        // Initialize the database
        deliveriesLineRepository.saveAndFlush(deliveriesLine);

        // Get the deliveriesLine
        restDeliveriesLineMockMvc.perform(get("/api/deliveries-lines/{id}", deliveriesLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deliveriesLine.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.doubleValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDeliveriesLine() throws Exception {
        // Get the deliveriesLine
        restDeliveriesLineMockMvc.perform(get("/api/deliveries-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeliveriesLine() throws Exception {
        // Initialize the database
        deliveriesLineRepository.saveAndFlush(deliveriesLine);
        int databaseSizeBeforeUpdate = deliveriesLineRepository.findAll().size();

        // Update the deliveriesLine
        DeliveriesLine updatedDeliveriesLine = deliveriesLineRepository.findOne(deliveriesLine.getId());
        // Disconnect from session so that the updates on updatedDeliveriesLine are not directly saved in db
        em.detach(updatedDeliveriesLine);
        updatedDeliveriesLine
            .reference(UPDATED_REFERENCE)
            .price(UPDATED_PRICE)
            .tax(UPDATED_TAX)
            .quantity(UPDATED_QUANTITY);
        DeliveriesLineDTO deliveriesLineDTO = deliveriesLineMapper.toDto(updatedDeliveriesLine);

        restDeliveriesLineMockMvc.perform(put("/api/deliveries-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveriesLineDTO)))
            .andExpect(status().isOk());

        // Validate the DeliveriesLine in the database
        List<DeliveriesLine> deliveriesLineList = deliveriesLineRepository.findAll();
        assertThat(deliveriesLineList).hasSize(databaseSizeBeforeUpdate);
        DeliveriesLine testDeliveriesLine = deliveriesLineList.get(deliveriesLineList.size() - 1);
        assertThat(testDeliveriesLine.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testDeliveriesLine.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testDeliveriesLine.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testDeliveriesLine.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingDeliveriesLine() throws Exception {
        int databaseSizeBeforeUpdate = deliveriesLineRepository.findAll().size();

        // Create the DeliveriesLine
        DeliveriesLineDTO deliveriesLineDTO = deliveriesLineMapper.toDto(deliveriesLine);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDeliveriesLineMockMvc.perform(put("/api/deliveries-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveriesLineDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveriesLine in the database
        List<DeliveriesLine> deliveriesLineList = deliveriesLineRepository.findAll();
        assertThat(deliveriesLineList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDeliveriesLine() throws Exception {
        // Initialize the database
        deliveriesLineRepository.saveAndFlush(deliveriesLine);
        int databaseSizeBeforeDelete = deliveriesLineRepository.findAll().size();

        // Get the deliveriesLine
        restDeliveriesLineMockMvc.perform(delete("/api/deliveries-lines/{id}", deliveriesLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeliveriesLine> deliveriesLineList = deliveriesLineRepository.findAll();
        assertThat(deliveriesLineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveriesLine.class);
        DeliveriesLine deliveriesLine1 = new DeliveriesLine();
        deliveriesLine1.setId(1L);
        DeliveriesLine deliveriesLine2 = new DeliveriesLine();
        deliveriesLine2.setId(deliveriesLine1.getId());
        assertThat(deliveriesLine1).isEqualTo(deliveriesLine2);
        deliveriesLine2.setId(2L);
        assertThat(deliveriesLine1).isNotEqualTo(deliveriesLine2);
        deliveriesLine1.setId(null);
        assertThat(deliveriesLine1).isNotEqualTo(deliveriesLine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveriesLineDTO.class);
        DeliveriesLineDTO deliveriesLineDTO1 = new DeliveriesLineDTO();
        deliveriesLineDTO1.setId(1L);
        DeliveriesLineDTO deliveriesLineDTO2 = new DeliveriesLineDTO();
        assertThat(deliveriesLineDTO1).isNotEqualTo(deliveriesLineDTO2);
        deliveriesLineDTO2.setId(deliveriesLineDTO1.getId());
        assertThat(deliveriesLineDTO1).isEqualTo(deliveriesLineDTO2);
        deliveriesLineDTO2.setId(2L);
        assertThat(deliveriesLineDTO1).isNotEqualTo(deliveriesLineDTO2);
        deliveriesLineDTO1.setId(null);
        assertThat(deliveriesLineDTO1).isNotEqualTo(deliveriesLineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(deliveriesLineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(deliveriesLineMapper.fromId(null)).isNull();
    }
}
