package com.nes.storetwo.dto;

import com.nes.storetwo.models.entity.Articulo;
import com.nes.storetwo.models.entity.Pedido;
public class PedidoArticuloDTO {
    private int cantidad;
    private Pedido pedido;
    private Articulo articulo;

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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
