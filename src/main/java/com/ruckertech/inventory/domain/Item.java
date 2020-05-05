package com.ruckertech.inventory.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

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

    @ManyToOne
    @JsonIgnoreProperties("items")
    private Location location;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public Item itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public byte[] getImage() {
        return image;
    }

    public Item image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Item imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getTag() {
        return tag;
    }

    public Item tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public Item qrCode(byte[] qrCode) {
        this.qrCode = qrCode;
        return this;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCodeContentType() {
        return qrCodeContentType;
    }

    public Item qrCodeContentType(String qrCodeContentType) {
        this.qrCodeContentType = qrCodeContentType;
        return this;
    }

    public void setQrCodeContentType(String qrCodeContentType) {
        this.qrCodeContentType = qrCodeContentType;
    }

    public byte[] getBarCode() {
        return barCode;
    }

    public Item barCode(byte[] barCode) {
        this.barCode = barCode;
        return this;
    }

    public void setBarCode(byte[] barCode) {
        this.barCode = barCode;
    }

    public String getBarCodeContentType() {
        return barCodeContentType;
    }

    public Item barCodeContentType(String barCodeContentType) {
        this.barCodeContentType = barCodeContentType;
        return this;
    }

    public void setBarCodeContentType(String barCodeContentType) {
        this.barCodeContentType = barCodeContentType;
    }

    public Location getLocation() {
        return location;
    }

    public Item location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return id != null && id.equals(((Item) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
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
