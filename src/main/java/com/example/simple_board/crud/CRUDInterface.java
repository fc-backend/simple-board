package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CRUDInterface<REQUEST, DTO> {

    DTO create(REQUEST request);

    Optional<DTO> read(Long id);

    DTO update(REQUEST request, Long id);

    void delete(Long id);

    Api<List<DTO>> list(Pageable pageable);

}
