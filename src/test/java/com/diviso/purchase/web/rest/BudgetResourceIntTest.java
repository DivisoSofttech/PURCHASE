package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.Budget;
import com.diviso.purchase.repository.BudgetRepository;
import com.diviso.purchase.service.BudgetService;
import com.diviso.purchase.service.dto.BudgetDTO;
import com.diviso.purchase.service.mapper.BudgetMapper;
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
 * Test class for the BudgetResource REST controller.
 *
 * @see BudgetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class BudgetResourceIntTest {

<<<<<<< HEAD
=======
    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

>>>>>>> ccc990b37e86ab7eca1e0986432ea2a89ac1e008
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBudgetMockMvc;

    private Budget budget;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BudgetResource budgetResource = new BudgetResource(budgetService);
        this.restBudgetMockMvc = MockMvcBuilders.standaloneSetup(budgetResource)
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
    public static Budget createEntity(EntityManager em) {
        Budget budget = new Budget()
<<<<<<< HEAD
=======
            .reference(DEFAULT_REFERENCE)
>>>>>>> ccc990b37e86ab7eca1e0986432ea2a89ac1e008
            .name(DEFAULT_NAME)
            .price(DEFAULT_PRICE);
        return budget;
    }

    @Before
    public void initTest() {
        budget = createEntity(em);
    }

    @Test
    @Transactional
    public void createBudget() throws Exception {
        int databaseSizeBeforeCreate = budgetRepository.findAll().size();

        // Create the Budget
        BudgetDTO budgetDTO = budgetMapper.toDto(budget);
        restBudgetMockMvc.perform(post("/api/budgets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(budgetDTO)))
            .andExpect(status().isCreated());

        // Validate the Budget in the database
        List<Budget> budgetList = budgetRepository.findAll();
        assertThat(budgetList).hasSize(databaseSizeBeforeCreate + 1);
        Budget testBudget = budgetList.get(budgetList.size() - 1);
<<<<<<< HEAD
=======
        assertThat(testBudget.getReference()).isEqualTo(DEFAULT_REFERENCE);
>>>>>>> ccc990b37e86ab7eca1e0986432ea2a89ac1e008
        assertThat(testBudget.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBudget.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createBudgetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = budgetRepository.findAll().size();

        // Create the Budget with an existing ID
        budget.setId(1L);
        BudgetDTO budgetDTO = budgetMapper.toDto(budget);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBudgetMockMvc.perform(post("/api/budgets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(budgetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Budget in the database
        List<Budget> budgetList = budgetRepository.findAll();
        assertThat(budgetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBudgets() throws Exception {
        // Initialize the database
        budgetRepository.saveAndFlush(budget);

        // Get all the budgetList
        restBudgetMockMvc.perform(get("/api/budgets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(budget.getId().intValue())))
<<<<<<< HEAD
=======
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
>>>>>>> ccc990b37e86ab7eca1e0986432ea2a89ac1e008
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    public void getBudget() throws Exception {
        // Initialize the database
        budgetRepository.saveAndFlush(budget);

        // Get the budget
        restBudgetMockMvc.perform(get("/api/budgets/{id}", budget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(budget.getId().intValue()))
<<<<<<< HEAD
=======
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
>>>>>>> ccc990b37e86ab7eca1e0986432ea2a89ac1e008
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBudget() throws Exception {
        // Get the budget
        restBudgetMockMvc.perform(get("/api/budgets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBudget() throws Exception {
        // Initialize the database
        budgetRepository.saveAndFlush(budget);
        int databaseSizeBeforeUpdate = budgetRepository.findAll().size();

        // Update the budget
        Budget updatedBudget = budgetRepository.findOne(budget.getId());
        // Disconnect from session so that the updates on updatedBudget are not directly saved in db
        em.detach(updatedBudget);
        updatedBudget
<<<<<<< HEAD
=======
            .reference(UPDATED_REFERENCE)
>>>>>>> ccc990b37e86ab7eca1e0986432ea2a89ac1e008
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE);
        BudgetDTO budgetDTO = budgetMapper.toDto(updatedBudget);

        restBudgetMockMvc.perform(put("/api/budgets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(budgetDTO)))
            .andExpect(status().isOk());

        // Validate the Budget in the database
        List<Budget> budgetList = budgetRepository.findAll();
        assertThat(budgetList).hasSize(databaseSizeBeforeUpdate);
        Budget testBudget = budgetList.get(budgetList.size() - 1);
<<<<<<< HEAD
=======
        assertThat(testBudget.getReference()).isEqualTo(UPDATED_REFERENCE);
>>>>>>> ccc990b37e86ab7eca1e0986432ea2a89ac1e008
        assertThat(testBudget.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBudget.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingBudget() throws Exception {
        int databaseSizeBeforeUpdate = budgetRepository.findAll().size();

        // Create the Budget
        BudgetDTO budgetDTO = budgetMapper.toDto(budget);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBudgetMockMvc.perform(put("/api/budgets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(budgetDTO)))
            .andExpect(status().isCreated());

        // Validate the Budget in the database
        List<Budget> budgetList = budgetRepository.findAll();
        assertThat(budgetList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBudget() throws Exception {
        // Initialize the database
        budgetRepository.saveAndFlush(budget);
        int databaseSizeBeforeDelete = budgetRepository.findAll().size();

        // Get the budget
        restBudgetMockMvc.perform(delete("/api/budgets/{id}", budget.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Budget> budgetList = budgetRepository.findAll();
        assertThat(budgetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Budget.class);
        Budget budget1 = new Budget();
        budget1.setId(1L);
        Budget budget2 = new Budget();
        budget2.setId(budget1.getId());
        assertThat(budget1).isEqualTo(budget2);
        budget2.setId(2L);
        assertThat(budget1).isNotEqualTo(budget2);
        budget1.setId(null);
        assertThat(budget1).isNotEqualTo(budget2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BudgetDTO.class);
        BudgetDTO budgetDTO1 = new BudgetDTO();
        budgetDTO1.setId(1L);
        BudgetDTO budgetDTO2 = new BudgetDTO();
        assertThat(budgetDTO1).isNotEqualTo(budgetDTO2);
        budgetDTO2.setId(budgetDTO1.getId());
        assertThat(budgetDTO1).isEqualTo(budgetDTO2);
        budgetDTO2.setId(2L);
        assertThat(budgetDTO1).isNotEqualTo(budgetDTO2);
        budgetDTO1.setId(null);
        assertThat(budgetDTO1).isNotEqualTo(budgetDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(budgetMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(budgetMapper.fromId(null)).isNull();
    }
}
