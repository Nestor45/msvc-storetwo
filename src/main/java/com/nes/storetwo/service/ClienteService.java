package com.nes.storetwo.service;

import com.nes.storetwo.dto.ClienteDTO;
import com.nes.storetwo.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<ClienteDTO> listarClientes();
    Optional<ClienteDTO> obtenerClienteId(Long id);
    ClienteDTO crearClient(ClienteDTO clienteDTO);

    void eliminarCliente(Long id);

}
