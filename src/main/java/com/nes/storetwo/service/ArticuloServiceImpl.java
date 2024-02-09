package com.nes.storetwo.service;

import com.nes.storetwo.dto.ArticuloDTO;
import com.nes.storetwo.models.entity.Articulo;
import com.nes.storetwo.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService{
    @Autowired
    ArticuloRepository articuloRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Articulo> listarArticulos() {
        return (List<Articulo>) articuloRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Articulo> obtenerArticuloId(Long id) {
        return articuloRepository.findById(id);
    }

    @Override
    @Transactional
    public Articulo crearArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCodigoArticulo(String codigo) {
        return articuloRepository.existsByCodigo(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticuloDTO> buscarCodigoArticulo(String codigo) {
        return articuloRepository.findArticuloByCodigo(codigo);
    }

}
