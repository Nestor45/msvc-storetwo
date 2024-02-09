package com.nes.storetwo.service;

import com.nes.storetwo.models.entity.PedidoArticulo;
import com.nes.storetwo.repository.PedidoArticuloRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoArticuloServiceImpl implements PedidoArticuloService{

    @Autowired
    private PedidoArticuloRespository pedidoArticuloRespository;

    @Override
    @Transactional
    public PedidoArticulo crearPedidoArticulo(PedidoArticulo pedidoArticulo) {
        return pedidoArticuloRespository.save(pedidoArticulo);
    }
}
