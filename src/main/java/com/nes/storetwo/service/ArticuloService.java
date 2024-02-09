package com.nes.storetwo.service;

import com.nes.storetwo.dto.ArticuloDTO;
import com.nes.storetwo.models.entity.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {
    List<ArticuloDTO> listarArticulos();
    Optional<ArticuloDTO> obtenerArticuloId(Long id);
    ArticuloDTO crearArticulo(ArticuloDTO articuloDTO);
    boolean existeCodigoArticulo(String codigo);
    Optional<ArticuloDTO> buscarCodigoArticulo(String codigo);
    void eliminarArticulo(Long id);
}
