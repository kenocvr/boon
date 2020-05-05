package com.ruckertech.inventory.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LocationMapperTest {

    private LocationMapper locationMapper;

    @BeforeEach
    public void setUp() {
        locationMapper = new LocationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(locationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(locationMapper.fromId(null)).isNull();
    }
}
