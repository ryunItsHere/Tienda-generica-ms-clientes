package com.tiendagenerica.ms_clientes.repository;

import com.tiendagenerica.ms_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCedula(String cedula);
    boolean existsByCedula(String cedula);
    boolean existsByEmail(String email);
}