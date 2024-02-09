package com.nes.storetwo.service;

import com.nes.storetwo.dto.ClienteDTO;
import com.nes.storetwo.models.entity.Cliente;
import com.nes.storetwo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findByStatus(1);
        List<ClienteDTO> listClienteDTOS = new ArrayList<>();
        clientes.forEach(cliente -> {
            ClienteDTO clienteDTO = convertirClienteADTO(cliente);
            listClienteDTOS.add(clienteDTO);
        });
        return listClienteDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> obtenerClienteId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        ClienteDTO clienteDTO = convertirClienteADTO(cliente.get());
        return Optional.of(clienteDTO);
    }

    @Override
    @Transactional
    public ClienteDTO crearClient(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setRazonSocial(clienteDTO.getRazonSocial());
        clienteRepository.save(cliente);
        return clienteDTO;
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        clienteRepository.eliminarCliente(id);
    }

    private ClienteDTO convertirClienteADTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setRazonSocial(cliente.getRazonSocial());
        return clienteDTO;
    }

}
