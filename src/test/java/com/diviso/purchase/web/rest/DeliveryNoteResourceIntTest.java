package com.diviso.purchase.web.rest;

import com.diviso.purchase.PurchaseApp;

import com.diviso.purchase.domain.DeliveryNote;
import com.diviso.purchase.repository.DeliveryNoteRepository;
import com.diviso.purchase.service.DeliveryNoteService;
import com.diviso.purchase.service.dto.DeliveryNoteDTO;
import com.diviso.purchase.service.mapper.DeliveryNoteMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.diviso.purchase.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DeliveryNoteResource REST controller.
 *
 * @see DeliveryNoteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PurchaseApp.class)
public class DeliveryNoteResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_REFERENCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DELIVERED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    @Autowired
    private DeliveryNoteMapper deliveryNoteMapper;

    @Autowired
    private DeliveryNoteService deliveryNoteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDeliveryNoteMockMvc;

    private DeliveryNote deliveryNote;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeliveryNoteResource deliveryNoteResource = new DeliveryNoteResource(deliveryNoteService);
        this.restDeliveryNoteMockMvc = MockMvcBuilders.standaloneSetup(deliveryNoteResource)
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
    public static DeliveryNote createEntity(EntityManager em) {
        DeliveryNote deliveryNote = new DeliveryNote()
            .reference(DEFAULT_REFERENCE)
            .orderReference(DEFAULT_ORDER_REFERENCE)
            .deliveredDate(DEFAULT_DELIVERED_DATE);
        return deliveryNote;
    }

    @Before
    public void initTest() {
        deliveryNote = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeliveryNote() throws Exception {
        int databaseSizeBeforeCreate = deliveryNoteRepository.findAll().size();

        // Create the DeliveryNote
        DeliveryNoteDTO deliveryNoteDTO = deliveryNoteMapper.toDto(deliveryNote);
        restDeliveryNoteMockMvc.perform(post("/api/delivery-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryNoteDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveryNote in the database
        List<DeliveryNote> deliveryNoteList = deliveryNoteRepository.findAll();
        assertThat(deliveryNoteList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryNote testDeliveryNote = deliveryNoteList.get(deliveryNoteList.size() - 1);
        assertThat(testDeliveryNote.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testDeliveryNote.getOrderReference()).isEqualTo(DEFAULT_ORDER_REFERENCE);
        assertThat(testDeliveryNote.getDeliveredDate()).isEqualTo(DEFAULT_DELIVERED_DATE);
    }

    @Test
    @Transactional
    public void createDeliveryNoteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryNoteRepository.findAll().size();

        // Create the DeliveryNote with an existing ID
        deliveryNote.setId(1L);
        DeliveryNoteDTO deliveryNoteDTO = deliveryNoteMapper.toDto(deliveryNote);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryNoteMockMvc.perform(post("/api/delivery-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryNoteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryNote in the database
        List<DeliveryNote> deliveryNoteList = deliveryNoteRepository.findAll();
        assertThat(deliveryNoteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeliveryNotes() throws Exception {
        // Initialize the database
        deliveryNoteRepository.saveAndFlush(deliveryNote);

        // Get all the deliveryNoteList
        restDeliveryNoteMockMvc.perform(get("/api/delivery-notes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryNote.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].orderReference").value(hasItem(DEFAULT_ORDER_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].deliveredDate").value(hasItem(DEFAULT_DELIVERED_DATE.toString())));
    }

    @Test
    @Transactional
    public void getDeliveryNote() throws Exception {
        // Initialize the database
        deliveryNoteRepository.saveAndFlush(deliveryNote);

        // Get the deliveryNote
        restDeliveryNoteMockMvc.perform(get("/api/delivery-notes/{id}", deliveryNote.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryNote.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.orderReference").value(DEFAULT_ORDER_REFERENCE.toString()))
            .andExpect(jsonPath("$.deliveredDate").value(DEFAULT_DELIVERED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeliveryNote() throws Exception {
        // Get the deliveryNote
        restDeliveryNoteMockMvc.perform(get("/api/delivery-notes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeliveryNote() throws Exception {
        // Initialize the database
        deliveryNoteRepository.saveAndFlush(deliveryNote);
        int databaseSizeBeforeUpdate = deliveryNoteRepository.findAll().size();

        // Update the deliveryNote
        DeliveryNote updatedDeliveryNote = deliveryNoteRepository.findOne(deliveryNote.getId());
        // Disconnect from session so that the updates on updatedDeliveryNote are not directly saved in db
        em.detach(updatedDeliveryNote);
        updatedDeliveryNote
            .reference(UPDATED_REFERENCE)
            .orderReference(UPDATED_ORDER_REFERENCE)
            .deliveredDate(UPDATED_DELIVERED_DATE);
        DeliveryNoteDTO deliveryNoteDTO = deliveryNoteMapper.toDto(updatedDeliveryNote);

        restDeliveryNoteMockMvc.perform(put("/api/delivery-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryNoteDTO)))
            .andExpect(status().isOk());

        // Validate the DeliveryNote in the database
        List<DeliveryNote> deliveryNoteList = deliveryNoteRepository.findAll();
        assertThat(deliveryNoteList).hasSize(databaseSizeBeforeUpdate);
        DeliveryNote testDeliveryNote = deliveryNoteList.get(deliveryNoteList.size() - 1);
        assertThat(testDeliveryNote.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testDeliveryNote.getOrderReference()).isEqualTo(UPDATED_ORDER_REFERENCE);
        assertThat(testDeliveryNote.getDeliveredDate()).isEqualTo(UPDATED_DELIVERED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDeliveryNote() throws Exception {
        int databaseSizeBeforeUpdate = deliveryNoteRepository.findAll().size();

        // Create the DeliveryNote
        DeliveryNoteDTO deliveryNoteDTO = deliveryNoteMapper.toDto(deliveryNote);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDeliveryNoteMockMvc.perform(put("/api/delivery-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryNoteDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveryNote in the database
        List<DeliveryNote> deliveryNoteList = deliveryNoteRepository.findAll();
        assertThat(deliveryNoteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDeliveryNote() throws Exception {
        // Initialize the database
        deliveryNoteRepository.saveAndFlush(deliveryNote);
        int databaseSizeBeforeDelete = deliveryNoteRepository.findAll().size();

        // Get the deliveryNote
        restDeliveryNoteMockMvc.perform(delete("/api/delivery-notes/{id}", deliveryNote.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeliveryNote> deliveryNoteList = deliveryNoteRepository.findAll();
        assertThat(deliveryNoteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryNote.class);
        DeliveryNote deliveryNote1 = new DeliveryNote();
        deliveryNote1.setId(1L);
        DeliveryNote deliveryNote2 = new DeliveryNote();
        deliveryNote2.setId(deliveryNote1.getId());
        assertThat(deliveryNote1).isEqualTo(deliveryNote2);
        deliveryNote2.setId(2L);
        assertThat(deliveryNote1).isNotEqualTo(deliveryNote2);
        deliveryNote1.setId(null);
        assertThat(deliveryNote1).isNotEqualTo(deliveryNote2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryNoteDTO.class);
        DeliveryNoteDTO deliveryNoteDTO1 = new DeliveryNoteDTO();
        deliveryNoteDTO1.setId(1L);
        DeliveryNoteDTO deliveryNoteDTO2 = new DeliveryNoteDTO();
        assertThat(deliveryNoteDTO1).isNotEqualTo(deliveryNoteDTO2);
        deliveryNoteDTO2.setId(deliveryNoteDTO1.getId());
        assertThat(deliveryNoteDTO1).isEqualTo(deliveryNoteDTO2);
        deliveryNoteDTO2.setId(2L);
        assertThat(deliveryNoteDTO1).isNotEqualTo(deliveryNoteDTO2);
        deliveryNoteDTO1.setId(null);
        assertThat(deliveryNoteDTO1).isNotEqualTo(deliveryNoteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(deliveryNoteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(deliveryNoteMapper.fromId(null)).isNull();
    }
}
