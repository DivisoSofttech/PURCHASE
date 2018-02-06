package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.PurchaseLine;
import com.diviso.purchase.repository.PurchaseLineRepository;
import com.diviso.purchase.service.PurchaseLineService;
import com.diviso.purchase.service.dto.PurchaseLineDTO;
import com.diviso.purchase.service.mapper.PurchaseLineMapper;
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
 * Test class for the PurchaseLineResource REST controller.
 *
 * @see PurchaseLineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class PurchaseLineResourceIntTest {

    private static final String DEFAULT_PRODUCT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_REFERENCE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRODUCT_PRICE = 1D;
    private static final Double UPDATED_PRODUCT_PRICE = 2D;

    private static final Double DEFAULT_PRODUCT_TAX = 1D;
    private static final Double UPDATED_PRODUCT_TAX = 2D;

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    @Autowired
    private PurchaseLineRepository purchaseLineRepository;

    @Autowired
    private PurchaseLineMapper purchaseLineMapper;

    @Autowired
    private PurchaseLineService purchaseLineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPurchaseLineMockMvc;

    private PurchaseLine purchaseLine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PurchaseLineResource purchaseLineResource = new PurchaseLineResource(purchaseLineService);
        this.restPurchaseLineMockMvc = MockMvcBuilders.standaloneSetup(purchaseLineResource)
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
    public static PurchaseLine createEntity(EntityManager em) {
        PurchaseLine purchaseLine = new PurchaseLine()
            .productReference(DEFAULT_PRODUCT_REFERENCE)
            .productPrice(DEFAULT_PRODUCT_PRICE)
            .productTax(DEFAULT_PRODUCT_TAX)
            .quantity(DEFAULT_QUANTITY);
        return purchaseLine;
    }

    @Before
    public void initTest() {
        purchaseLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createPurchaseLine() throws Exception {
        int databaseSizeBeforeCreate = purchaseLineRepository.findAll().size();

        // Create the PurchaseLine
        PurchaseLineDTO purchaseLineDTO = purchaseLineMapper.toDto(purchaseLine);
        restPurchaseLineMockMvc.perform(post("/api/purchase-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseLineDTO)))
            .andExpect(status().isCreated());

        // Validate the PurchaseLine in the database
        List<PurchaseLine> purchaseLineList = purchaseLineRepository.findAll();
        assertThat(purchaseLineList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseLine testPurchaseLine = purchaseLineList.get(purchaseLineList.size() - 1);
        assertThat(testPurchaseLine.getProductReference()).isEqualTo(DEFAULT_PRODUCT_REFERENCE);
        assertThat(testPurchaseLine.getProductPrice()).isEqualTo(DEFAULT_PRODUCT_PRICE);
        assertThat(testPurchaseLine.getProductTax()).isEqualTo(DEFAULT_PRODUCT_TAX);
        assertThat(testPurchaseLine.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createPurchaseLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = purchaseLineRepository.findAll().size();

        // Create the PurchaseLine with an existing ID
        purchaseLine.setId(1L);
        PurchaseLineDTO purchaseLineDTO = purchaseLineMapper.toDto(purchaseLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseLineMockMvc.perform(post("/api/purchase-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PurchaseLine in the database
        List<PurchaseLine> purchaseLineList = purchaseLineRepository.findAll();
        assertThat(purchaseLineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPurchaseLines() throws Exception {
        // Initialize the database
        purchaseLineRepository.saveAndFlush(purchaseLine);

        // Get all the purchaseLineList
        restPurchaseLineMockMvc.perform(get("/api/purchase-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].productReference").value(hasItem(DEFAULT_PRODUCT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].productPrice").value(hasItem(DEFAULT_PRODUCT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].productTax").value(hasItem(DEFAULT_PRODUCT_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())));
    }

    @Test
    @Transactional
    public void getPurchaseLine() throws Exception {
        // Initialize the database
        purchaseLineRepository.saveAndFlush(purchaseLine);

        // Get the purchaseLine
        restPurchaseLineMockMvc.perform(get("/api/purchase-lines/{id}", purchaseLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseLine.getId().intValue()))
            .andExpect(jsonPath("$.productReference").value(DEFAULT_PRODUCT_REFERENCE.toString()))
            .andExpect(jsonPath("$.productPrice").value(DEFAULT_PRODUCT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.productTax").value(DEFAULT_PRODUCT_TAX.doubleValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPurchaseLine() throws Exception {
        // Get the purchaseLine
        restPurchaseLineMockMvc.perform(get("/api/purchase-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePurchaseLine() throws Exception {
        // Initialize the database
        purchaseLineRepository.saveAndFlush(purchaseLine);
        int databaseSizeBeforeUpdate = purchaseLineRepository.findAll().size();

        // Update the purchaseLine
        PurchaseLine updatedPurchaseLine = purchaseLineRepository.findOne(purchaseLine.getId());
        // Disconnect from session so that the updates on updatedPurchaseLine are not directly saved in db
        em.detach(updatedPurchaseLine);
        updatedPurchaseLine
            .productReference(UPDATED_PRODUCT_REFERENCE)
            .productPrice(UPDATED_PRODUCT_PRICE)
            .productTax(UPDATED_PRODUCT_TAX)
            .quantity(UPDATED_QUANTITY);
        PurchaseLineDTO purchaseLineDTO = purchaseLineMapper.toDto(updatedPurchaseLine);

        restPurchaseLineMockMvc.perform(put("/api/purchase-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseLineDTO)))
            .andExpect(status().isOk());

        // Validate the PurchaseLine in the database
        List<PurchaseLine> purchaseLineList = purchaseLineRepository.findAll();
        assertThat(purchaseLineList).hasSize(databaseSizeBeforeUpdate);
        PurchaseLine testPurchaseLine = purchaseLineList.get(purchaseLineList.size() - 1);
        assertThat(testPurchaseLine.getProductReference()).isEqualTo(UPDATED_PRODUCT_REFERENCE);
        assertThat(testPurchaseLine.getProductPrice()).isEqualTo(UPDATED_PRODUCT_PRICE);
        assertThat(testPurchaseLine.getProductTax()).isEqualTo(UPDATED_PRODUCT_TAX);
        assertThat(testPurchaseLine.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingPurchaseLine() throws Exception {
        int databaseSizeBeforeUpdate = purchaseLineRepository.findAll().size();

        // Create the PurchaseLine
        PurchaseLineDTO purchaseLineDTO = purchaseLineMapper.toDto(purchaseLine);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPurchaseLineMockMvc.perform(put("/api/purchase-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseLineDTO)))
            .andExpect(status().isCreated());

        // Validate the PurchaseLine in the database
        List<PurchaseLine> purchaseLineList = purchaseLineRepository.findAll();
        assertThat(purchaseLineList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePurchaseLine() throws Exception {
        // Initialize the database
        purchaseLineRepository.saveAndFlush(purchaseLine);
        int databaseSizeBeforeDelete = purchaseLineRepository.findAll().size();

        // Get the purchaseLine
        restPurchaseLineMockMvc.perform(delete("/api/purchase-lines/{id}", purchaseLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PurchaseLine> purchaseLineList = purchaseLineRepository.findAll();
        assertThat(purchaseLineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseLine.class);
        PurchaseLine purchaseLine1 = new PurchaseLine();
        purchaseLine1.setId(1L);
        PurchaseLine purchaseLine2 = new PurchaseLine();
        purchaseLine2.setId(purchaseLine1.getId());
        assertThat(purchaseLine1).isEqualTo(purchaseLine2);
        purchaseLine2.setId(2L);
        assertThat(purchaseLine1).isNotEqualTo(purchaseLine2);
        purchaseLine1.setId(null);
        assertThat(purchaseLine1).isNotEqualTo(purchaseLine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseLineDTO.class);
        PurchaseLineDTO purchaseLineDTO1 = new PurchaseLineDTO();
        purchaseLineDTO1.setId(1L);
        PurchaseLineDTO purchaseLineDTO2 = new PurchaseLineDTO();
        assertThat(purchaseLineDTO1).isNotEqualTo(purchaseLineDTO2);
        purchaseLineDTO2.setId(purchaseLineDTO1.getId());
        assertThat(purchaseLineDTO1).isEqualTo(purchaseLineDTO2);
        purchaseLineDTO2.setId(2L);
        assertThat(purchaseLineDTO1).isNotEqualTo(purchaseLineDTO2);
        purchaseLineDTO1.setId(null);
        assertThat(purchaseLineDTO1).isNotEqualTo(purchaseLineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(purchaseLineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(purchaseLineMapper.fromId(null)).isNull();
    }
}
