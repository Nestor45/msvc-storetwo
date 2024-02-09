package com.nes.storetwo.service;

import com.nes.storetwo.dto.ArticuloDTO;
import com.nes.storetwo.models.entity.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {
    List<Articulo> listarArticulos();
    Optional<Articulo> obtenerArticuloId(Long id);
    Articulo crearArticulo(Articulo articulo);
    boolean existeCodigoArticulo(String codigo);
    Optional<ArticuloDTO> buscarCodigoArticulo(String codigo);
}
