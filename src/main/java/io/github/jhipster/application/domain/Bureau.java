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
 * A Bureau.
 */
@Entity
@Table(name = "bureau")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bureau implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "espace")
    private String espace;

    @OneToMany(mappedBy = "place")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Poste> postes = new HashSet<>();

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

    public Bureau name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEspace() {
        return espace;
    }

    public Bureau espace(String espace) {
        this.espace = espace;
        return this;
    }

    public void setEspace(String espace) {
        this.espace = espace;
    }

    public Set<Poste> getPostes() {
        return postes;
    }

    public Bureau postes(Set<Poste> postes) {
        this.postes = postes;
        return this;
    }

    public Bureau addPostes(Poste poste) {
        this.postes.add(poste);
        poste.setPlace(this);
        return this;
    }

    public Bureau removePostes(Poste poste) {
        this.postes.remove(poste);
        poste.setPlace(null);
        return this;
    }

    public void setPostes(Set<Poste> postes) {
        this.postes = postes;
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
        Bureau bureau = (Bureau) o;
        if (bureau.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bureau.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bureau{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", espace='" + getEspace() + "'" +
            "}";
    }
}
