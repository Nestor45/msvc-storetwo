package com.nes.storetwo.repository;

import com.nes.storetwo.models.entity.Articulo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long> {
    boolean existsByCodigo(String codigo);

    Articulo findArticuloByCodigo(String codigo);
}
