package com.ruckertech.inventory.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.ruckertech.inventory.domain.Item} entity. This class is used
 * in {@link com.ruckertech.inventory.web.rest.ItemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /items?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ItemCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter itemName;

    private StringFilter tag;

    private LongFilter locationId;

    public ItemCriteria() {
    }

    public ItemCriteria(ItemCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.itemName = other.itemName == null ? null : other.itemName.copy();
        this.tag = other.tag == null ? null : other.tag.copy();
        this.locationId = other.locationId == null ? null : other.locationId.copy();
    }

    @Override
    public ItemCriteria copy() {
        return new ItemCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getItemName() {
        return itemName;
    }

    public void setItemName(StringFilter itemName) {
        this.itemName = itemName;
    }

    public StringFilter getTag() {
        return tag;
    }

    public void setTag(StringFilter tag) {
        this.tag = tag;
    }

    public LongFilter getLocationId() {
        return locationId;
    }

    public void setLocationId(LongFilter locationId) {
        this.locationId = locationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ItemCriteria that = (ItemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(itemName, that.itemName) &&
            Objects.equals(tag, that.tag) &&
            Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        itemName,
        tag,
        locationId
        );
    }

    @Override
    public String toString() {
        return "ItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (itemName != null ? "itemName=" + itemName + ", " : "") +
                (tag != null ? "tag=" + tag + ", " : "") +
                (locationId != null ? "locationId=" + locationId + ", " : "") +
            "}";
    }

}
