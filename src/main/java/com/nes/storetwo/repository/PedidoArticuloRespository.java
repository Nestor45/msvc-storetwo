package com.nes.storetwo.repository;

import com.nes.storetwo.models.entity.PedidoArticulo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoArticuloRespository extends CrudRepository<PedidoArticulo, Long> {
}
