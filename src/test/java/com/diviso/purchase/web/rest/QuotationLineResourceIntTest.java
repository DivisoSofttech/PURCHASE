package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.QuotationLine;
import com.diviso.purchase.repository.QuotationLineRepository;
import com.diviso.purchase.service.QuotationLineService;
import com.diviso.purchase.service.dto.QuotationLineDTO;
import com.diviso.purchase.service.mapper.QuotationLineMapper;
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
 * Test class for the QuotationLineResource REST controller.
 *
 * @see QuotationLineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class QuotationLineResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRICE = 1;
    private static final Integer UPDATED_PRICE = 2;

    private static final Double DEFAULT_TAX = 1D;
    private static final Double UPDATED_TAX = 2D;

    private static final Double DEFAULT_AVAILABLE_QUANTITY = 1D;
    private static final Double UPDATED_AVAILABLE_QUANTITY = 2D;

    private static final Boolean DEFAULT_IS_SELECT = false;
    private static final Boolean UPDATED_IS_SELECT = true;

    @Autowired
    private QuotationLineRepository quotationLineRepository;

    @Autowired
    private QuotationLineMapper quotationLineMapper;

    @Autowired
    private QuotationLineService quotationLineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuotationLineMockMvc;

    private QuotationLine quotationLine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuotationLineResource quotationLineResource = new QuotationLineResource(quotationLineService);
        this.restQuotationLineMockMvc = MockMvcBuilders.standaloneSetup(quotationLineResource)
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
    public static QuotationLine createEntity(EntityManager em) {
        QuotationLine quotationLine = new QuotationLine()
            .reference(DEFAULT_REFERENCE)
            .price(DEFAULT_PRICE)
            .tax(DEFAULT_TAX)
            .availableQuantity(DEFAULT_AVAILABLE_QUANTITY)
            .isSelect(DEFAULT_IS_SELECT);
        return quotationLine;
    }

    @Before
    public void initTest() {
        quotationLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuotationLine() throws Exception {
        int databaseSizeBeforeCreate = quotationLineRepository.findAll().size();

        // Create the QuotationLine
        QuotationLineDTO quotationLineDTO = quotationLineMapper.toDto(quotationLine);
        restQuotationLineMockMvc.perform(post("/api/quotation-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationLineDTO)))
            .andExpect(status().isCreated());

        // Validate the QuotationLine in the database
        List<QuotationLine> quotationLineList = quotationLineRepository.findAll();
        assertThat(quotationLineList).hasSize(databaseSizeBeforeCreate + 1);
        QuotationLine testQuotationLine = quotationLineList.get(quotationLineList.size() - 1);
        assertThat(testQuotationLine.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testQuotationLine.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testQuotationLine.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testQuotationLine.getAvailableQuantity()).isEqualTo(DEFAULT_AVAILABLE_QUANTITY);
        assertThat(testQuotationLine.isIsSelect()).isEqualTo(DEFAULT_IS_SELECT);
    }

    @Test
    @Transactional
    public void createQuotationLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quotationLineRepository.findAll().size();

        // Create the QuotationLine with an existing ID
        quotationLine.setId(1L);
        QuotationLineDTO quotationLineDTO = quotationLineMapper.toDto(quotationLine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuotationLineMockMvc.perform(post("/api/quotation-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationLineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuotationLine in the database
        List<QuotationLine> quotationLineList = quotationLineRepository.findAll();
        assertThat(quotationLineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuotationLines() throws Exception {
        // Initialize the database
        quotationLineRepository.saveAndFlush(quotationLine);

        // Get all the quotationLineList
        restQuotationLineMockMvc.perform(get("/api/quotation-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quotationLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].availableQuantity").value(hasItem(DEFAULT_AVAILABLE_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].isSelect").value(hasItem(DEFAULT_IS_SELECT.booleanValue())));
    }

    @Test
    @Transactional
    public void getQuotationLine() throws Exception {
        // Initialize the database
        quotationLineRepository.saveAndFlush(quotationLine);

        // Get the quotationLine
        restQuotationLineMockMvc.perform(get("/api/quotation-lines/{id}", quotationLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quotationLine.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.doubleValue()))
            .andExpect(jsonPath("$.availableQuantity").value(DEFAULT_AVAILABLE_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.isSelect").value(DEFAULT_IS_SELECT.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQuotationLine() throws Exception {
        // Get the quotationLine
        restQuotationLineMockMvc.perform(get("/api/quotation-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuotationLine() throws Exception {
        // Initialize the database
        quotationLineRepository.saveAndFlush(quotationLine);
        int databaseSizeBeforeUpdate = quotationLineRepository.findAll().size();

        // Update the quotationLine
        QuotationLine updatedQuotationLine = quotationLineRepository.findOne(quotationLine.getId());
        // Disconnect from session so that the updates on updatedQuotationLine are not directly saved in db
        em.detach(updatedQuotationLine);
        updatedQuotationLine
            .reference(UPDATED_REFERENCE)
            .price(UPDATED_PRICE)
            .tax(UPDATED_TAX)
            .availableQuantity(UPDATED_AVAILABLE_QUANTITY)
            .isSelect(UPDATED_IS_SELECT);
        QuotationLineDTO quotationLineDTO = quotationLineMapper.toDto(updatedQuotationLine);

        restQuotationLineMockMvc.perform(put("/api/quotation-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationLineDTO)))
            .andExpect(status().isOk());

        // Validate the QuotationLine in the database
        List<QuotationLine> quotationLineList = quotationLineRepository.findAll();
        assertThat(quotationLineList).hasSize(databaseSizeBeforeUpdate);
        QuotationLine testQuotationLine = quotationLineList.get(quotationLineList.size() - 1);
        assertThat(testQuotationLine.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testQuotationLine.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testQuotationLine.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testQuotationLine.getAvailableQuantity()).isEqualTo(UPDATED_AVAILABLE_QUANTITY);
        assertThat(testQuotationLine.isIsSelect()).isEqualTo(UPDATED_IS_SELECT);
    }

    @Test
    @Transactional
    public void updateNonExistingQuotationLine() throws Exception {
        int databaseSizeBeforeUpdate = quotationLineRepository.findAll().size();

        // Create the QuotationLine
        QuotationLineDTO quotationLineDTO = quotationLineMapper.toDto(quotationLine);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQuotationLineMockMvc.perform(put("/api/quotation-lines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationLineDTO)))
            .andExpect(status().isCreated());

        // Validate the QuotationLine in the database
        List<QuotationLine> quotationLineList = quotationLineRepository.findAll();
        assertThat(quotationLineList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQuotationLine() throws Exception {
        // Initialize the database
        quotationLineRepository.saveAndFlush(quotationLine);
        int databaseSizeBeforeDelete = quotationLineRepository.findAll().size();

        // Get the quotationLine
        restQuotationLineMockMvc.perform(delete("/api/quotation-lines/{id}", quotationLine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuotationLine> quotationLineList = quotationLineRepository.findAll();
        assertThat(quotationLineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotationLine.class);
        QuotationLine quotationLine1 = new QuotationLine();
        quotationLine1.setId(1L);
        QuotationLine quotationLine2 = new QuotationLine();
        quotationLine2.setId(quotationLine1.getId());
        assertThat(quotationLine1).isEqualTo(quotationLine2);
        quotationLine2.setId(2L);
        assertThat(quotationLine1).isNotEqualTo(quotationLine2);
        quotationLine1.setId(null);
        assertThat(quotationLine1).isNotEqualTo(quotationLine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotationLineDTO.class);
        QuotationLineDTO quotationLineDTO1 = new QuotationLineDTO();
        quotationLineDTO1.setId(1L);
        QuotationLineDTO quotationLineDTO2 = new QuotationLineDTO();
        assertThat(quotationLineDTO1).isNotEqualTo(quotationLineDTO2);
        quotationLineDTO2.setId(quotationLineDTO1.getId());
        assertThat(quotationLineDTO1).isEqualTo(quotationLineDTO2);
        quotationLineDTO2.setId(2L);
        assertThat(quotationLineDTO1).isNotEqualTo(quotationLineDTO2);
        quotationLineDTO1.setId(null);
        assertThat(quotationLineDTO1).isNotEqualTo(quotationLineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quotationLineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quotationLineMapper.fromId(null)).isNull();
    }
}
