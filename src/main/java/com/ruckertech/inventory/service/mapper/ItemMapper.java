package com.ruckertech.inventory.service.mapper;


import com.ruckertech.inventory.domain.*;
import com.ruckertech.inventory.service.dto.ItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Item} and its DTO {@link ItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Mapping(source = "location.id", target = "locationId")
    ItemDTO toDto(Item item);

    @Mapping(source = "locationId", target = "location")
    Item toEntity(ItemDTO itemDTO);

    default Item fromId(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
