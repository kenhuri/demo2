package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Personne.
 */
@Entity
@Table(name = "personne")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "client")
    private String client;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @OneToMany(mappedBy = "place")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bureau> personnes = new HashSet<>();

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

    public Personne name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public Personne client(String client) {
        this.client = client;
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public byte[] getImage() {
        return image;
    }

    public Personne image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Personne imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Set<Bureau> getPersonnes() {
        return personnes;
    }

    public Personne personnes(Set<Bureau> bureaus) {
        this.personnes = bureaus;
        return this;
    }

    public Personne addPersonne(Bureau bureau) {
        this.personnes.add(bureau);
        bureau.setPlace(this);
        return this;
    }

    public Personne removePersonne(Bureau bureau) {
        this.personnes.remove(bureau);
        bureau.setPlace(null);
        return this;
    }

    public void setPersonnes(Set<Bureau> bureaus) {
        this.personnes = bureaus;
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
        Personne personne = (Personne) o;
        if (personne.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personne.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Personne{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", client='" + getClient() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
