package com.nes.storetwo.service;

import com.nes.storetwo.dto.ArticuloDTO;
import com.nes.storetwo.dto.ClienteDTO;
import com.nes.storetwo.models.entity.Articulo;
import com.nes.storetwo.models.entity.Cliente;
import com.nes.storetwo.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService{
    @Autowired
    ArticuloRepository articuloRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ArticuloDTO> listarArticulos() {
        List<Articulo> articulos = (List<Articulo>) articuloRepository.findAll();
        List<ArticuloDTO> articulosDTO = new ArrayList<>();
        articulos.forEach(articulo -> {
            ArticuloDTO articuloDTO = convertirArticuloADTO(articulo);
            articulosDTO.add(articuloDTO);
        });
        return articulosDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticuloDTO> obtenerArticuloId(Long id) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        ArticuloDTO articuloDTO=  convertirArticuloADTO(articulo.get());
        return Optional.of(articuloDTO);
    }

    @Override
    @Transactional
    public ArticuloDTO crearArticulo(ArticuloDTO articuloDTO) {
        Articulo articulo = new Articulo();

        articulo.setNombre(articuloDTO.getNombre());
        articulo.setCodigo(articuloDTO.getCodigo());
        articulo.setPrecio(articuloDTO.getPrecio());
        articulo.setStock(articuloDTO.getStock());

        articuloRepository.save(articulo);

        return articuloDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCodigoArticulo(String codigo) {
        return articuloRepository.existsByCodigo(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticuloDTO> buscarCodigoArticulo(String codigo) {
        ArticuloDTO articuloDTO = convertirArticuloADTO(articuloRepository.findArticuloByCodigo(codigo));
        return Optional.of(articuloDTO);
    }

    @Override
    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }

    private ArticuloDTO convertirArticuloADTO(Articulo articulo) {
        ArticuloDTO articuloDTO = new ArticuloDTO();

        articuloDTO.setNombre(articulo.getNombre());
        articuloDTO.setCodigo(articulo.getCodigo());
        articuloDTO.setPrecio(articulo.getPrecio());
        articuloDTO.setStock(articulo.getStock());

        return articuloDTO;
    }

}
