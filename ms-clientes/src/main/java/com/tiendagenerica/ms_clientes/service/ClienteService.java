package com.tiendagenerica.ms_clientes.service;

import com.tiendagenerica.ms_clientes.dto.ClienteDTO;
import com.tiendagenerica.ms_clientes.model.Cliente;
import com.tiendagenerica.ms_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // ─────────────────────────────────────────
    // Crear cliente
    // ─────────────────────────────────────────
    public Cliente crearCliente(ClienteDTO dto) {

        if (clienteRepository.existsByCedula(dto.getCedula())) {
            throw new RuntimeException(
                    "La cédula ya está registrada: "
                            + dto.getCedula());
        }
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException(
                    "El email ya está registrado: "
                            + dto.getEmail());
        }

        Cliente cliente = new Cliente();
        cliente.setCedula(dto.getCedula());
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setCiudad(dto.getCiudad());

        return clienteRepository.save(cliente);
    }

    // ─────────────────────────────────────────
    // Listar todos
    // ─────────────────────────────────────────
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // ─────────────────────────────────────────
    // Buscar por cédula
    // ─────────────────────────────────────────
    public Cliente buscarPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException(
                        "Cliente no encontrado con cédula: "
                                + cedula));
    }

    // ─────────────────────────────────────────
    // Actualizar cliente
    // ─────────────────────────────────────────
    public Cliente actualizarCliente(String cedula,
                                     ClienteDTO dto) {
        Cliente cliente = clienteRepository
                .findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException(
                        "Cliente no encontrado con cédula: "
                                + cedula));

        // Verifica email solo si cambió
        if (!cliente.getEmail().equals(dto.getEmail()) &&
                clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException(
                    "El email ya está en uso: " + dto.getEmail());
        }

        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setCiudad(dto.getCiudad());

        return clienteRepository.save(cliente);
    }

    // ─────────────────────────────────────────
    // Eliminar cliente
    // ─────────────────────────────────────────
    public void eliminarCliente(String cedula) {
        Cliente cliente = clienteRepository
                .findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException(
                        "Cliente no encontrado con cédula: "
                                + cedula));
        clienteRepository.delete(cliente);
    }

    // ─────────────────────────────────────────
    // Verificar existencia por cédula
    // → usado por MS-Ventas
    // ─────────────────────────────────────────
    public boolean existePorCedula(String cedula) {
        return clienteRepository.existsByCedula(cedula);
    }
}