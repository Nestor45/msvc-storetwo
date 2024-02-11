package com.nes.storetwo.Controller;

import com.nes.storetwo.dto.PedidoArticuloDTO;
import com.nes.storetwo.service.PedidoArticuloService;
import com.nes.storetwo.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoArticuloService pedidoArticuloService;
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear-con-articulos")
    public ResponseEntity<?> crearPedidoConArticulo(@Valid @RequestBody PedidoArticuloDTO pedidoArticuloDTO, BindingResult result) {

        if (result.hasErrors()) {
            return validarObjeto(result);
        }
        PedidoArticuloDTO pedidoArticuloDTODb = pedidoArticuloService.crearPedidoConArticulo(pedidoArticuloDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Pedido creado con éxito");
        response.put("data", pedidoArticuloDTODb);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private static ResponseEntity<Map<String, String>> validarObjeto(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
