package com.ruckertech.inventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Location.
 */
@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "tag")
    private String tag;

    @Lob
    @Column(name = "qr_code")
    private byte[] qrCode;

    @Column(name = "qr_code_content_type")
    private String qrCodeContentType;

    @Lob
    @Column(name = "bar_code")
    private byte[] barCode;

    @Column(name = "bar_code_content_type")
    private String barCodeContentType;

    @OneToMany(mappedBy = "location")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Item> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public Location locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public byte[] getImage() {
        return image;
    }

    public Location image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Location imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getTag() {
        return tag;
    }

    public Location tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public Location qrCode(byte[] qrCode) {
        this.qrCode = qrCode;
        return this;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCodeContentType() {
        return qrCodeContentType;
    }

    public Location qrCodeContentType(String qrCodeContentType) {
        this.qrCodeContentType = qrCodeContentType;
        return this;
    }

    public void setQrCodeContentType(String qrCodeContentType) {
        this.qrCodeContentType = qrCodeContentType;
    }

    public byte[] getBarCode() {
        return barCode;
    }

    public Location barCode(byte[] barCode) {
        this.barCode = barCode;
        return this;
    }

    public void setBarCode(byte[] barCode) {
        this.barCode = barCode;
    }

    public String getBarCodeContentType() {
        return barCodeContentType;
    }

    public Location barCodeContentType(String barCodeContentType) {
        this.barCodeContentType = barCodeContentType;
        return this;
    }

    public void setBarCodeContentType(String barCodeContentType) {
        this.barCodeContentType = barCodeContentType;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Location items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Location addItem(Item item) {
        this.items.add(item);
        item.setLocation(this);
        return this;
    }

    public Location removeItem(Item item) {
        this.items.remove(item);
        item.setLocation(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        return id != null && id.equals(((Location) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", locationName='" + getLocationName() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", tag='" + getTag() + "'" +
            ", qrCode='" + getQrCode() + "'" +
            ", qrCodeContentType='" + getQrCodeContentType() + "'" +
            ", barCode='" + getBarCode() + "'" +
            ", barCodeContentType='" + getBarCodeContentType() + "'" +
            "}";
    }
}
