package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Poste entity.
 */
public class PosteDTO implements Serializable {

    private Long id;

    private String name;

    private Integer quotePart;

    private Long placeId;

    private String placeName;

    private Long habilitationId;

    private String habilitationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuotePart() {
        return quotePart;
    }

    public void setQuotePart(Integer quotePart) {
        this.quotePart = quotePart;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long bureauId) {
        this.placeId = bureauId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String bureauName) {
        this.placeName = bureauName;
    }

    public Long getHabilitationId() {
        return habilitationId;
    }

    public void setHabilitationId(Long personneId) {
        this.habilitationId = personneId;
    }

    public String getHabilitationName() {
        return habilitationName;
    }

    public void setHabilitationName(String personneName) {
        this.habilitationName = personneName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PosteDTO posteDTO = (PosteDTO) o;
        if (posteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), posteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PosteDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", quotePart=" + getQuotePart() +
            ", place=" + getPlaceId() +
            ", place='" + getPlaceName() + "'" +
            ", habilitation=" + getHabilitationId() +
            ", habilitation='" + getHabilitationName() + "'" +
            "}";
    }
}
