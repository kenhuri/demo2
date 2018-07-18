package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Poste.
 */
@Entity
@Table(name = "poste")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Poste implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quote_part")
    private Integer quotePart;

    @ManyToOne
    @JsonIgnoreProperties("postes")
    private Bureau place;

    @ManyToOne
    @JsonIgnoreProperties("travails")
    private Personne habilitation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Poste name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuotePart() {
        return quotePart;
    }

    public Poste quotePart(Integer quotePart) {
        this.quotePart = quotePart;
        return this;
    }

    public void setQuotePart(Integer quotePart) {
        this.quotePart = quotePart;
    }

    public Bureau getPlace() {
        return place;
    }

    public Poste place(Bureau bureau) {
        this.place = bureau;
        return this;
    }

    public void setPlace(Bureau bureau) {
        this.place = bureau;
    }

    public Personne getHabilitation() {
        return habilitation;
    }

    public Poste habilitation(Personne personne) {
        this.habilitation = personne;
        return this;
    }

    public void setHabilitation(Personne personne) {
        this.habilitation = personne;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Poste poste = (Poste) o;
        if (poste.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), poste.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Poste{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", quotePart=" + getQuotePart() +
            "}";
    }
}
