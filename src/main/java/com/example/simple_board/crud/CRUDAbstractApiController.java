package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class CRUDAbstractApiController<REQUEST, DTO, ENTITY> implements CRUDInterface<REQUEST, DTO> {

    @Autowired(required = false)
    private CRUDAbstractService<REQUEST, DTO, ENTITY> crudAbstractService;

    @PostMapping("")
    @Override
    public DTO create(
            @Valid
            @RequestBody
            REQUEST request
    ) {
        return crudAbstractService.create(request);
    }

    @GetMapping("/id/{id}")
    @Override
    public Optional<DTO> read(
            @PathVariable
            Long id
    ) {
        return crudAbstractService.read(id);
    }

    @PutMapping("/id/{id}")
    @Override
    public DTO update(
            @Valid
            @RequestBody REQUEST request,
            @PathVariable Long id
    ) {
        return crudAbstractService.update(request, id);
    }

    @DeleteMapping("/id/{id}")
    @Override
    public void delete(
            @PathVariable
            Long id
    ) {
        crudAbstractService.delete(id);
    }

    @GetMapping("/all")
    @Override
    public Api<List<DTO>> list(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return crudAbstractService.list(pageable);
    }

}
