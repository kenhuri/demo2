package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PosteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Poste and its DTO PosteDTO.
 */
@Mapper(componentModel = "spring", uses = {BureauMapper.class, PersonneMapper.class})
public interface PosteMapper extends EntityMapper<PosteDTO, Poste> {

    @Mapping(source = "place.id", target = "placeId")
    @Mapping(source = "place.name", target = "placeName")
    @Mapping(source = "habilitation.id", target = "habilitationId")
    @Mapping(source = "habilitation.name", target = "habilitationName")
    PosteDTO toDto(Poste poste);

    @Mapping(source = "placeId", target = "place")
    @Mapping(source = "habilitationId", target = "habilitation")
    Poste toEntity(PosteDTO posteDTO);

    default Poste fromId(Long id) {
        if (id == null) {
            return null;
        }
        Poste poste = new Poste();
        poste.setId(id);
        return poste;
    }
}
