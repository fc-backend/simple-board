package com.example.simple_board.crud;

public interface Converter<REQUEST, DTO, ENTITY> {

    DTO toDtoFromRequest(REQUEST request);

    DTO toDtoFromEntity(ENTITY entity);

    ENTITY toEntityFromDto(DTO dto);

    ENTITY updateEntity(ENTITY oldEntity, ENTITY newEntity);
}
