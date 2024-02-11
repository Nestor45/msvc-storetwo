package com.nes.storetwo.Controller;

import com.nes.storetwo.dto.ArticuloDTO;
import com.nes.storetwo.models.entity.Articulo;
import com.nes.storetwo.repository.ArticuloRepository;
import com.nes.storetwo.service.ArticuloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {
    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloRepository articuloRepository;

    @GetMapping
    public ResponseEntity<?> obtenerArticulos() {
        return ResponseEntity.ok().body(articuloService.listarArticulos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerArticulo(@Valid @PathVariable Long id) {
        return  ResponseEntity.ok().body(articuloService.obtenerArticuloId(id));
    }

    @PostMapping
    public ResponseEntity<?> crearArticulo(@Valid @RequestBody ArticuloDTO articuloDTO, BindingResult result) {
        if (result.hasErrors()) {
            return validarObjeto(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(articuloService.crearArticulo(articuloDTO));
    }

    @PutMapping("/editar-articulo/{id}")
    public ResponseEntity<?> editarArticulo(@Valid @RequestBody ArticuloDTO articuloDTO, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validarObjeto(result);
        }
        Optional<Articulo> articuloOptional = articuloRepository.findById(id);
        if (articuloOptional.isPresent()) {
            Articulo articuloDb = articuloOptional.get();
            System.out.println("Actualizando...");
            articuloDb.setNombre(articuloDTO.getNombre());
            articuloDb.setCodigo(articuloDTO.getCodigo());
            articuloDb.setPrecio(articuloDTO.getPrecio());
            articuloDb.setStock(articuloDTO.getStock());

            articuloRepository.save(articuloDb);

            return ResponseEntity.ok().body(articuloDTO);
        }
        return ResponseEntity.badRequest().body(result.getFieldErrors());
    }

    @GetMapping("/filtro-codigo")
    public ResponseEntity<?> buscarCodigo(@Valid @RequestParam String codigo) {
        Map<String, Object> response = new HashMap<>();
        Optional<ArticuloDTO> articuloDTOOptionall= articuloService.buscarCodigoArticulo(codigo);
        if (articuloDTOOptionall.isPresent()) {
            response.put("message", "El articulo con el codigo: "+ codigo + "fue encontrado");
            response.put("data", articuloDTOOptionall.get());
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/eliminar-articulo/{id}")
    public ResponseEntity<?> eliminarCodigo(@Valid @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Articulo> articuloOptional = articuloRepository.findById(id);
        if (articuloOptional.isPresent()) {
            Articulo articuloDb = articuloOptional.get();
            articuloService.eliminarArticulo(id);
            response.put("message", "Dato eliminado con exito");
            response.put("data", articuloDb);
            return ResponseEntity.ok().body(response);
        }
        response.put("message", "Error algo salio mal al eliminar el dato");
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
