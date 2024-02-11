package com.nes.storetwo.service;

import com.nes.storetwo.dto.PedidoDTO;
import com.nes.storetwo.models.entity.Pedido;
import com.nes.storetwo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listarPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> obtenerPedidoId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public boolean existeCodigoPedido(String codigo) {
        return pedidoRepository.existsByCodigo(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PedidoDTO> buscarCodigoPedido(String codigo) {
        return pedidoRepository.findPedidoByCodigo(codigo);
    }


}
