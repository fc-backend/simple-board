package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CRUDAbstractService<REQUEST, DTO, ENTITY> implements CRUDInterface<REQUEST, DTO> {

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> jpaRepository;

    @Autowired(required = false)
    private Converter<REQUEST, DTO, ENTITY> converter;

    @Override
    public DTO create(REQUEST request) {

        // 1. REQUEST -> DTO
        DTO dto = converter.toDtoFromRequest(request);

        // 2. DTO -> ENTITY
        ENTITY entity = converter.toEntityFromDto(dto);

        // 3. 저장
        jpaRepository.save(entity);

        // save -> dto
        return converter.toDtoFromEntity(entity);
    }

    @Override
    public Optional<DTO> read(Long id) {

        return jpaRepository.findById(id)
                .map(converter::toDtoFromEntity);

    }

    @Override
    public DTO update(REQUEST request, Long id) {

        // 1. 기존 엔티티 조회
        ENTITY oldEntity = jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 엔티티가 존재하지 않습니다." + id));

        // 2. REQUEST -> DTO
        DTO dto = converter.toDtoFromRequest(request);

        // 3. DTO -> ENTITY
        ENTITY newEntity = converter.toEntityFromDto(dto);

        // 4. 업데이트
        ENTITY updatedEntity = converter.updateEntity(oldEntity, newEntity);

        // 5. 저장
        jpaRepository.save(updatedEntity);

        // 6. ENTITY -> DTO
        return converter.toDtoFromEntity(updatedEntity);

    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {

        var list = jpaRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElement(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPages(list.getTotalPages())
                .build();

        var dtoList = list.stream()
                .map( it -> converter.toDtoFromEntity(it) )
                .collect(Collectors.toList());

        return Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();
    }
}