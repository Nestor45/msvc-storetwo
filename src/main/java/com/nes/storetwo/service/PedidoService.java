package com.nes.storetwo.service;

import com.nes.storetwo.dto.PedidoDTO;
import com.nes.storetwo.models.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> listarPedidos();

    Optional<Pedido> obtenerPedidoId(Long id);

    Pedido crearPedido(Pedido pedido);

    boolean existeCodigoPedido(String codigo);

    Optional<PedidoDTO> buscarCodigoPedido(String codigo);

}
