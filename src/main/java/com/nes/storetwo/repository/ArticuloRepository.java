package com.nes.storetwo.repository;

import com.nes.storetwo.dto.ArticuloDTO;
import com.nes.storetwo.models.entity.Articulo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long> {
    boolean existsByCodigo(String codigo);

    Optional<ArticuloDTO> findArticuloByCodigo(String codigo);
}
