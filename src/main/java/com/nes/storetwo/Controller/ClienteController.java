package com.nes.storetwo.Controller;

import com.nes.storetwo.dto.ClienteDTO;
import com.nes.storetwo.models.entity.Cliente;
import com.nes.storetwo.repository.ClienteRepository;
import com.nes.storetwo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        return ResponseEntity.ok().body(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.obtenerClienteId(id));
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return validarObjeto(result);
        }
        ClienteDTO clienteDTODb = clienteService.crearClient(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTODb);
    }

    @PutMapping("/editar-cliente/{id}")
    public ResponseEntity<?> editarCliente(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validarObjeto(result);
        }
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            System.out.println("Actualizando...");

            Cliente clienteDb = optionalCliente.get();

            clienteDb.setNombre(clienteDTO.getNombre());
            clienteDb.setRazonSocial(clienteDTO.getRazonSocial());

            clienteRepository.save(clienteDb);
//            clienteService.crearClient(clienteDTO);

            return ResponseEntity.ok().body(clienteDTO);
        }
        return ResponseEntity.badRequest().body(result.getFieldErrors());
    }

    @PutMapping("/eliminar-cliente/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            System.out.println("Eliminando...");
            clienteService.eliminarCliente(cliente.getId());
            response.put("message: ","Se a eliminado el Cliente");
            response.put("data: ", optionalCliente.get().getNombre());
            return ResponseEntity.ok().body(response);
        }
        response.put("message: ","Error al eliminar el Cliente");
        return ResponseEntity.badRequest().body(response);
    }

    private static ResponseEntity<Map<String, String>> validarObjeto(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
