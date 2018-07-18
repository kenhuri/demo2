package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Bureau;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bureau entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BureauRepository extends JpaRepository<Bureau, Long> {

}
