package com.nes.storetwo.dto;

import com.nes.storetwo.models.entity.Articulo;
import com.nes.storetwo.models.entity.Pedido;

import java.util.List;

public class PedidoArticuloDTO {
    private int cantidad;
    private Pedido pedido;
    private Long clienteId;
    private Long articuloId;
    private List<PedidoArticuloDTO> articulos;
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    public List<PedidoArticuloDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<PedidoArticuloDTO> articulos) {
        this.articulos = articulos;
    }

}
