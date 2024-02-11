package com.nes.storetwo.service;

import com.nes.storetwo.dto.PedidoArticuloDTO;
import com.nes.storetwo.dto.PedidoDTO;
import com.nes.storetwo.models.entity.Articulo;
import com.nes.storetwo.models.entity.Pedido;
import com.nes.storetwo.models.entity.PedidoArticulo;

import java.util.Optional;

public interface PedidoArticuloService {

    PedidoArticuloDTO crearPedidoConArticulo(PedidoArticuloDTO pedidoArticuloDTO);

    PedidoArticulo crearPedidoArticulo(PedidoArticulo pedidoArticulo);

    Optional<PedidoArticulo> guaradarPedidoconArticulo(Pedido pedido, Articulo articulo, int cantidad);
}
