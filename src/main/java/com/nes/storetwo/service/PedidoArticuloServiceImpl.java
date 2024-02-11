package com.nes.storetwo.service;

import com.nes.storetwo.dto.ClienteDTO;
import com.nes.storetwo.dto.PedidoArticuloDTO;
import com.nes.storetwo.dto.PedidoDTO;
import com.nes.storetwo.models.entity.Articulo;
import com.nes.storetwo.models.entity.Cliente;
import com.nes.storetwo.models.entity.Pedido;
import com.nes.storetwo.models.entity.PedidoArticulo;
import com.nes.storetwo.repository.ArticuloRepository;
import com.nes.storetwo.repository.ClienteRepository;
import com.nes.storetwo.repository.PedidoArticuloRespository;
import com.nes.storetwo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PedidoArticuloServiceImpl implements PedidoArticuloService{

    @Autowired
    private PedidoArticuloRespository pedidoArticuloRespository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Override
    @Transactional
    public PedidoArticuloDTO crearPedidoConArticulo(PedidoArticuloDTO pedidoArticuloDTO) {
        Pedido pedido = pedidoArticuloDTO.getPedido();
        Pedido pedidoCreado = pedidoRepository.save(pedido);
        Optional<Cliente> clienteOptional = clienteRepository.findById(pedidoArticuloDTO.getClienteId());
        if (clienteOptional.isPresent()) {
            Cliente clienteDb = clienteOptional.get();
            pedidoCreado.setCliente(clienteDb);
            pedidoArticuloDTO.getArticulos().forEach(articulo -> {
                int cantidad = articulo.getCantidad();
                Optional<Articulo> articuloOptional = articuloRepository.findById(articulo.getArticuloId());
                if (articuloOptional.isPresent()) {
                    Articulo articuloDb = articuloOptional.get();
                    if (articuloDb.getStock() > 0 ) { // Vericamos que se tengas articulos
                        Optional<PedidoArticulo> pedidoArticuloOptional = guaradarPedidoconArticulo(pedidoCreado, articuloDb, cantidad);
                        if (pedidoArticuloOptional.isPresent()) {
                            articuloDb.setStock(articuloDb.getStock() - cantidad);
                            articuloRepository.save(articuloDb);
                        }
                    }
                }
            });
//            for (PedidoArticuloDTO pedidoArticulo: pedidoArticuloDTO.getArticulos()) {
//                System.out.println("articuloOptional");
//                Optional<Articulo> articuloOptional = articuloRepository.findById(pedidoArticulo.getArticuloId());
//
//                if (articuloOptional.isPresent()) {
//                    Articulo articuloDb = articuloOptional.get();
//                    System.out.println(articuloDb.getId());
//                    System.out.println(articuloDb.getNombre());
//                    Optional<PedidoArticulo> pedidoArticuloOptional = agregarPedidoconArticulo(pedidoCreado, articuloDb, pedidoArticulo.getCantidad());
//                    if (pedidoArticuloOptional.isPresent()) {
//                        articuloDb.setStock(articuloDb.getStock() - pedidoArticulo.getCantidad());
//                        articuloRepository.save(articuloDb);
//                    }
//                }
//            }
        }
        return pedidoArticuloDTO;
    }

    @Override
    @Transactional
    public PedidoArticulo crearPedidoArticulo(PedidoArticulo pedidoArticulo) {
        return pedidoArticuloRespository.save(pedidoArticulo);
    }

    @Override
    public Optional<PedidoArticulo> guaradarPedidoconArticulo(Pedido pedido, Articulo articulo, int cantidad) {
        PedidoArticulo pedidoArticulo = new PedidoArticulo();
        pedidoArticulo.setPedido(pedido);
        pedidoArticulo.setArticulo(articulo);
        pedidoArticulo.setCantidad(cantidad);
        return Optional.of(pedidoArticuloRespository.save(pedidoArticulo));
    }
}
