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
 * Criteria class for the {@link com.ruckertech.inventory.domain.Location} entity. This class is used
 * in {@link com.ruckertech.inventory.web.rest.LocationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /locations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LocationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter locationName;

    private StringFilter tag;

    private LongFilter itemId;

    public LocationCriteria() {
    }

    public LocationCriteria(LocationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.locationName = other.locationName == null ? null : other.locationName.copy();
        this.tag = other.tag == null ? null : other.tag.copy();
        this.itemId = other.itemId == null ? null : other.itemId.copy();
    }

    @Override
    public LocationCriteria copy() {
        return new LocationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLocationName() {
        return locationName;
    }

    public void setLocationName(StringFilter locationName) {
        this.locationName = locationName;
    }

    public StringFilter getTag() {
        return tag;
    }

    public void setTag(StringFilter tag) {
        this.tag = tag;
    }

    public LongFilter getItemId() {
        return itemId;
    }

    public void setItemId(LongFilter itemId) {
        this.itemId = itemId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LocationCriteria that = (LocationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(locationName, that.locationName) &&
            Objects.equals(tag, that.tag) &&
            Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        locationName,
        tag,
        itemId
        );
    }

    @Override
    public String toString() {
        return "LocationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (locationName != null ? "locationName=" + locationName + ", " : "") +
                (tag != null ? "tag=" + tag + ", " : "") +
                (itemId != null ? "itemId=" + itemId + ", " : "") +
            "}";
    }

}
