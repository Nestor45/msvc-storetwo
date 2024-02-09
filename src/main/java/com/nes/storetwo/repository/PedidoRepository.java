package com.nes.storetwo.repository;

import com.nes.storetwo.dto.PedidoDTO;
import com.nes.storetwo.models.entity.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    boolean existsByCodigo(String codigo);

    Optional<PedidoDTO> findPedidoByCodigo(String codigo);
}
