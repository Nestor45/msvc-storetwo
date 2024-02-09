package com.nes.storetwo.repository;

import com.nes.storetwo.models.entity.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    List<Cliente> findByStatus(int status);
    @Modifying
    @Query("UPDATE Cliente e SET e.status = 0 WHERE e.id=?1")
    void eliminarCliente(Long id);


}
