package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.DeliveryLine;
import com.diviso.purchase.repository.DeliveryLineRepository;
import com.diviso.purchase.service.DeliveryLineService;
import com.diviso.purchase.service.dto.DeliveryLineDTO;
import com.diviso.purchase.service.mapper.DeliveryLineMapper;
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
 * Test class for the DeliveryLineResource REST controller.
 *
 * @see DeliveryLineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class DeliveryLineResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_TAX = 1D;
    private static final Double UPDATED_TAX = 2D;

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    @Autowired
    private DeliveryLineRepository deliveryLineRepository;

    @Autowired
    private DeliveryLineMapper deliveryLineMapper;

    @Autowired
    private DeliveryLineService deliveryLineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDeliveryLineMockMvc;

    private DeliveryLine deliveryLine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeliveryLineResource deliveryLineResource = new DeliveryLineResource(deliveryLineService);
        this.restDeliveryLineMockMvc = MockMvcBuilders.standaloneSetup(deliveryLineResource)
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
    public static DeliveryLine createEntity(EntityManager em) {
        DeliveryLine deliveryLine = new DeliveryLine()
            .reference(DEFAULT_REFERENCE)
            .price(DEFAULT_PRICE)
            .tax(DEFAULT_TAX)
            .quantity(DEFAULT_QUANTITY);
        return deliveryLine;
    }

    @Before
    public void initTest() {
        deliveryLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeliveryLine() throws Exception {
        int databaseSizeBeforeCreate = deliveryLineRepository.findAll().size();

        // Create the DeliveryLine
        DeliveryLineDTO deliveryLineDTO = deliveryLineMapper.toDto(deliveryLine);
        restDeliveryLineMockMvc.perform(post("/api/delivery-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryLineDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveryLine in the database
        List<DeliveryLine> deliveryLineList = deliveryLineRepository.findAll();
        assertThat(deliveryLineList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryLine testDeliveryLine = deliveryLineList.get(deliveryLineList.size() - 1);
        assertThat(testDeliveryLine.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testDeliveryLine.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testDeliveryLine.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testDeliveryLine.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createDeliveryLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryLineRepository.findAll().size();

        // Create the DeliveryLine with an existing ID
        deliveryLine.setId(1L);
        DeliveryLineDTO deliveryLineDTO = deliveryLineMapper.toDto(deliveryLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryLineMockMvc.perform(post("/api/delivery-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryLine in the database
        List<DeliveryLine> deliveryLineList = deliveryLineRepository.findAll();
        assertThat(deliveryLineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeliveryLines() throws Exception {
        // Initialize the database
        deliveryLineRepository.saveAndFlush(deliveryLine);

        // Get all the deliveryLineList
        restDeliveryLineMockMvc.perform(get("/api/delivery-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())));
    }

    @Test
    @Transactional
    public void getDeliveryLine() throws Exception {
        // Initialize the database
        deliveryLineRepository.saveAndFlush(deliveryLine);

        // Get the deliveryLine
        restDeliveryLineMockMvc.perform(get("/api/delivery-lines/{id}", deliveryLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryLine.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.doubleValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDeliveryLine() throws Exception {
        // Get the deliveryLine
        restDeliveryLineMockMvc.perform(get("/api/delivery-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeliveryLine() throws Exception {
        // Initialize the database
        deliveryLineRepository.saveAndFlush(deliveryLine);
        int databaseSizeBeforeUpdate = deliveryLineRepository.findAll().size();

        // Update the deliveryLine
        DeliveryLine updatedDeliveryLine = deliveryLineRepository.findOne(deliveryLine.getId());
        // Disconnect from session so that the updates on updatedDeliveryLine are not directly saved in db
        em.detach(updatedDeliveryLine);
        updatedDeliveryLine
            .reference(UPDATED_REFERENCE)
            .price(UPDATED_PRICE)
            .tax(UPDATED_TAX)
            .quantity(UPDATED_QUANTITY);
        DeliveryLineDTO deliveryLineDTO = deliveryLineMapper.toDto(updatedDeliveryLine);

        restDeliveryLineMockMvc.perform(put("/api/delivery-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryLineDTO)))
            .andExpect(status().isOk());

        // Validate the DeliveryLine in the database
        List<DeliveryLine> deliveryLineList = deliveryLineRepository.findAll();
        assertThat(deliveryLineList).hasSize(databaseSizeBeforeUpdate);
        DeliveryLine testDeliveryLine = deliveryLineList.get(deliveryLineList.size() - 1);
        assertThat(testDeliveryLine.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testDeliveryLine.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testDeliveryLine.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testDeliveryLine.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingDeliveryLine() throws Exception {
        int databaseSizeBeforeUpdate = deliveryLineRepository.findAll().size();

        // Create the DeliveryLine
        DeliveryLineDTO deliveryLineDTO = deliveryLineMapper.toDto(deliveryLine);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDeliveryLineMockMvc.perform(put("/api/delivery-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryLineDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveryLine in the database
        List<DeliveryLine> deliveryLineList = deliveryLineRepository.findAll();
        assertThat(deliveryLineList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDeliveryLine() throws Exception {
        // Initialize the database
        deliveryLineRepository.saveAndFlush(deliveryLine);
        int databaseSizeBeforeDelete = deliveryLineRepository.findAll().size();

        // Get the deliveryLine
        restDeliveryLineMockMvc.perform(delete("/api/delivery-lines/{id}", deliveryLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeliveryLine> deliveryLineList = deliveryLineRepository.findAll();
        assertThat(deliveryLineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryLine.class);
        DeliveryLine deliveryLine1 = new DeliveryLine();
        deliveryLine1.setId(1L);
        DeliveryLine deliveryLine2 = new DeliveryLine();
        deliveryLine2.setId(deliveryLine1.getId());
        assertThat(deliveryLine1).isEqualTo(deliveryLine2);
        deliveryLine2.setId(2L);
        assertThat(deliveryLine1).isNotEqualTo(deliveryLine2);
        deliveryLine1.setId(null);
        assertThat(deliveryLine1).isNotEqualTo(deliveryLine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryLineDTO.class);
        DeliveryLineDTO deliveryLineDTO1 = new DeliveryLineDTO();
        deliveryLineDTO1.setId(1L);
        DeliveryLineDTO deliveryLineDTO2 = new DeliveryLineDTO();
        assertThat(deliveryLineDTO1).isNotEqualTo(deliveryLineDTO2);
        deliveryLineDTO2.setId(deliveryLineDTO1.getId());
        assertThat(deliveryLineDTO1).isEqualTo(deliveryLineDTO2);
        deliveryLineDTO2.setId(2L);
        assertThat(deliveryLineDTO1).isNotEqualTo(deliveryLineDTO2);
        deliveryLineDTO1.setId(null);
        assertThat(deliveryLineDTO1).isNotEqualTo(deliveryLineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(deliveryLineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(deliveryLineMapper.fromId(null)).isNull();
    }
}
