package com.ruckertech.inventory.web.rest;

import com.ruckertech.inventory.InventoryNoElasticApp;
import com.ruckertech.inventory.domain.Location;
import com.ruckertech.inventory.domain.Item;
import com.ruckertech.inventory.repository.LocationRepository;
import com.ruckertech.inventory.service.LocationService;
import com.ruckertech.inventory.service.dto.LocationDTO;
import com.ruckertech.inventory.service.mapper.LocationMapper;
import com.ruckertech.inventory.service.dto.LocationCriteria;
import com.ruckertech.inventory.service.LocationQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LocationResource} REST controller.
 */
@SpringBootTest(classes = InventoryNoElasticApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LocationResourceIT {

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final byte[] DEFAULT_QR_CODE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_QR_CODE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_QR_CODE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_QR_CODE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_BAR_CODE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BAR_CODE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BAR_CODE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BAR_CODE_CONTENT_TYPE = "image/png";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationQueryService locationQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocationMockMvc;

    private Location location;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createEntity(EntityManager em) {
        Location location = new Location()
            .locationName(DEFAULT_LOCATION_NAME)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .tag(DEFAULT_TAG)
            .qrCode(DEFAULT_QR_CODE)
            .qrCodeContentType(DEFAULT_QR_CODE_CONTENT_TYPE)
            .barCode(DEFAULT_BAR_CODE)
            .barCodeContentType(DEFAULT_BAR_CODE_CONTENT_TYPE);
        return location;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createUpdatedEntity(EntityManager em) {
        Location location = new Location()
            .locationName(UPDATED_LOCATION_NAME)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .tag(UPDATED_TAG)
            .qrCode(UPDATED_QR_CODE)
            .qrCodeContentType(UPDATED_QR_CODE_CONTENT_TYPE)
            .barCode(UPDATED_BAR_CODE)
            .barCodeContentType(UPDATED_BAR_CODE_CONTENT_TYPE);
        return location;
    }

    @BeforeEach
    public void initTest() {
        location = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocation() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isCreated());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate + 1);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testLocation.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testLocation.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testLocation.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testLocation.getQrCode()).isEqualTo(DEFAULT_QR_CODE);
        assertThat(testLocation.getQrCodeContentType()).isEqualTo(DEFAULT_QR_CODE_CONTENT_TYPE);
        assertThat(testLocation.getBarCode()).isEqualTo(DEFAULT_BAR_CODE);
        assertThat(testLocation.getBarCodeContentType()).isEqualTo(DEFAULT_BAR_CODE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location with an existing ID
        location.setId(1L);
        LocationDTO locationDTO = locationMapper.toDto(location);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLocations() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].qrCodeContentType").value(hasItem(DEFAULT_QR_CODE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].qrCode").value(hasItem(Base64Utils.encodeToString(DEFAULT_QR_CODE))))
            .andExpect(jsonPath("$.[*].barCodeContentType").value(hasItem(DEFAULT_BAR_CODE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].barCode").value(hasItem(Base64Utils.encodeToString(DEFAULT_BAR_CODE))));
    }
    
    @Test
    @Transactional
    public void getLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", location.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(location.getId().intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.qrCodeContentType").value(DEFAULT_QR_CODE_CONTENT_TYPE))
            .andExpect(jsonPath("$.qrCode").value(Base64Utils.encodeToString(DEFAULT_QR_CODE)))
            .andExpect(jsonPath("$.barCodeContentType").value(DEFAULT_BAR_CODE_CONTENT_TYPE))
            .andExpect(jsonPath("$.barCode").value(Base64Utils.encodeToString(DEFAULT_BAR_CODE)));
    }


    @Test
    @Transactional
    public void getLocationsByIdFiltering() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        Long id = location.getId();

        defaultLocationShouldBeFound("id.equals=" + id);
        defaultLocationShouldNotBeFound("id.notEquals=" + id);

        defaultLocationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLocationShouldNotBeFound("id.greaterThan=" + id);

        defaultLocationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLocationShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLocationsByLocationNameIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName equals to DEFAULT_LOCATION_NAME
        defaultLocationShouldBeFound("locationName.equals=" + DEFAULT_LOCATION_NAME);

        // Get all the locationList where locationName equals to UPDATED_LOCATION_NAME
        defaultLocationShouldNotBeFound("locationName.equals=" + UPDATED_LOCATION_NAME);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName not equals to DEFAULT_LOCATION_NAME
        defaultLocationShouldNotBeFound("locationName.notEquals=" + DEFAULT_LOCATION_NAME);

        // Get all the locationList where locationName not equals to UPDATED_LOCATION_NAME
        defaultLocationShouldBeFound("locationName.notEquals=" + UPDATED_LOCATION_NAME);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNameIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName in DEFAULT_LOCATION_NAME or UPDATED_LOCATION_NAME
        defaultLocationShouldBeFound("locationName.in=" + DEFAULT_LOCATION_NAME + "," + UPDATED_LOCATION_NAME);

        // Get all the locationList where locationName equals to UPDATED_LOCATION_NAME
        defaultLocationShouldNotBeFound("locationName.in=" + UPDATED_LOCATION_NAME);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName is not null
        defaultLocationShouldBeFound("locationName.specified=true");

        // Get all the locationList where locationName is null
        defaultLocationShouldNotBeFound("locationName.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByLocationNameContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName contains DEFAULT_LOCATION_NAME
        defaultLocationShouldBeFound("locationName.contains=" + DEFAULT_LOCATION_NAME);

        // Get all the locationList where locationName contains UPDATED_LOCATION_NAME
        defaultLocationShouldNotBeFound("locationName.contains=" + UPDATED_LOCATION_NAME);
    }

    @Test
    @Transactional
    public void getAllLocationsByLocationNameNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where locationName does not contain DEFAULT_LOCATION_NAME
        defaultLocationShouldNotBeFound("locationName.doesNotContain=" + DEFAULT_LOCATION_NAME);

        // Get all the locationList where locationName does not contain UPDATED_LOCATION_NAME
        defaultLocationShouldBeFound("locationName.doesNotContain=" + UPDATED_LOCATION_NAME);
    }


    @Test
    @Transactional
    public void getAllLocationsByTagIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where tag equals to DEFAULT_TAG
        defaultLocationShouldBeFound("tag.equals=" + DEFAULT_TAG);

        // Get all the locationList where tag equals to UPDATED_TAG
        defaultLocationShouldNotBeFound("tag.equals=" + UPDATED_TAG);
    }

    @Test
    @Transactional
    public void getAllLocationsByTagIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where tag not equals to DEFAULT_TAG
        defaultLocationShouldNotBeFound("tag.notEquals=" + DEFAULT_TAG);

        // Get all the locationList where tag not equals to UPDATED_TAG
        defaultLocationShouldBeFound("tag.notEquals=" + UPDATED_TAG);
    }

    @Test
    @Transactional
    public void getAllLocationsByTagIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where tag in DEFAULT_TAG or UPDATED_TAG
        defaultLocationShouldBeFound("tag.in=" + DEFAULT_TAG + "," + UPDATED_TAG);

        // Get all the locationList where tag equals to UPDATED_TAG
        defaultLocationShouldNotBeFound("tag.in=" + UPDATED_TAG);
    }

    @Test
    @Transactional
    public void getAllLocationsByTagIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where tag is not null
        defaultLocationShouldBeFound("tag.specified=true");

        // Get all the locationList where tag is null
        defaultLocationShouldNotBeFound("tag.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByTagContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where tag contains DEFAULT_TAG
        defaultLocationShouldBeFound("tag.contains=" + DEFAULT_TAG);

        // Get all the locationList where tag contains UPDATED_TAG
        defaultLocationShouldNotBeFound("tag.contains=" + UPDATED_TAG);
    }

    @Test
    @Transactional
    public void getAllLocationsByTagNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where tag does not contain DEFAULT_TAG
        defaultLocationShouldNotBeFound("tag.doesNotContain=" + DEFAULT_TAG);

        // Get all the locationList where tag does not contain UPDATED_TAG
        defaultLocationShouldBeFound("tag.doesNotContain=" + UPDATED_TAG);
    }


    @Test
    @Transactional
    public void getAllLocationsByItemIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);
        Item item = ItemResourceIT.createEntity(em);
        em.persist(item);
        em.flush();
        location.addItem(item);
        locationRepository.saveAndFlush(location);
        Long itemId = item.getId();

        // Get all the locationList where item equals to itemId
        defaultLocationShouldBeFound("itemId.equals=" + itemId);

        // Get all the locationList where item equals to itemId + 1
        defaultLocationShouldNotBeFound("itemId.equals=" + (itemId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLocationShouldBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].qrCodeContentType").value(hasItem(DEFAULT_QR_CODE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].qrCode").value(hasItem(Base64Utils.encodeToString(DEFAULT_QR_CODE))))
            .andExpect(jsonPath("$.[*].barCodeContentType").value(hasItem(DEFAULT_BAR_CODE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].barCode").value(hasItem(Base64Utils.encodeToString(DEFAULT_BAR_CODE))));

        // Check, that the count call also returns 1
        restLocationMockMvc.perform(get("/api/locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLocationShouldNotBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLocationMockMvc.perform(get("/api/locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLocation() throws Exception {
        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Update the location
        Location updatedLocation = locationRepository.findById(location.getId()).get();
        // Disconnect from session so that the updates on updatedLocation are not directly saved in db
        em.detach(updatedLocation);
        updatedLocation
            .locationName(UPDATED_LOCATION_NAME)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .tag(UPDATED_TAG)
            .qrCode(UPDATED_QR_CODE)
            .qrCodeContentType(UPDATED_QR_CODE_CONTENT_TYPE)
            .barCode(UPDATED_BAR_CODE)
            .barCodeContentType(UPDATED_BAR_CODE_CONTENT_TYPE);
        LocationDTO locationDTO = locationMapper.toDto(updatedLocation);

        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isOk());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testLocation.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testLocation.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testLocation.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testLocation.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testLocation.getQrCodeContentType()).isEqualTo(UPDATED_QR_CODE_CONTENT_TYPE);
        assertThat(testLocation.getBarCode()).isEqualTo(UPDATED_BAR_CODE);
        assertThat(testLocation.getBarCodeContentType()).isEqualTo(UPDATED_BAR_CODE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingLocation() throws Exception {
        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        int databaseSizeBeforeDelete = locationRepository.findAll().size();

        // Delete the location
        restLocationMockMvc.perform(delete("/api/locations/{id}", location.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
