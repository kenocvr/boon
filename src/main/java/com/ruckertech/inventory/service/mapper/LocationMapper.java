package com.ruckertech.inventory.service.mapper;


import com.ruckertech.inventory.domain.*;
import com.ruckertech.inventory.service.dto.LocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {


    @Mapping(target = "items", ignore = true)
    @Mapping(target = "removeItem", ignore = true)
    Location toEntity(LocationDTO locationDTO);

    default Location fromId(Long id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
