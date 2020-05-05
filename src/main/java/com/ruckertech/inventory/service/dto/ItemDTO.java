package com.ruckertech.inventory.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.ruckertech.inventory.domain.Item} entity.
 */
public class ItemDTO implements Serializable {
    
    private Long id;

    private String itemName;

    @Lob
    private byte[] image;

    private String imageContentType;
    private String tag;

    @Lob
    private byte[] qrCode;

    private String qrCodeContentType;
    @Lob
    private byte[] barCode;

    private String barCodeContentType;

    private Long locationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCodeContentType() {
        return qrCodeContentType;
    }

    public void setQrCodeContentType(String qrCodeContentType) {
        this.qrCodeContentType = qrCodeContentType;
    }

    public byte[] getBarCode() {
        return barCode;
    }

    public void setBarCode(byte[] barCode) {
        this.barCode = barCode;
    }

    public String getBarCodeContentType() {
        return barCodeContentType;
    }

    public void setBarCodeContentType(String barCodeContentType) {
        this.barCodeContentType = barCodeContentType;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
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

        ItemDTO itemDTO = (ItemDTO) o;
        if (itemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", image='" + getImage() + "'" +
            ", tag='" + getTag() + "'" +
            ", qrCode='" + getQrCode() + "'" +
            ", barCode='" + getBarCode() + "'" +
            ", locationId=" + getLocationId() +
            "}";
    }
}
