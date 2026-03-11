package com.tiendagenerica.ms_clientes.controller;

import com.tiendagenerica.ms_clientes.dto.ClienteDTO;
import com.tiendagenerica.ms_clientes.model.Cliente;
import com.tiendagenerica.ms_clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Crear cliente (ADMIN y EMPLEADO)
    @PostMapping("/crear")
    public ResponseEntity<Cliente> crear(
            @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(
                clienteService.crearCliente(dto));
    }

    // Listar todos (ADMIN y EMPLEADO)
    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(
                clienteService.listarTodos());
    }

    // Buscar por cédula (ADMIN y EMPLEADO)
    @GetMapping("/buscar/{cedula}")
    public ResponseEntity<Cliente> buscar(
            @PathVariable String cedula) {
        return ResponseEntity.ok(
                clienteService.buscarPorCedula(cedula));
    }

    // Actualizar cliente (ADMIN y EMPLEADO)
    @PutMapping("/actualizar/{cedula}")
    public ResponseEntity<Cliente> actualizar(
            @PathVariable String cedula,
            @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(
                clienteService.actualizarCliente(cedula, dto));
    }

    // Eliminar cliente (solo ADMIN)
    @DeleteMapping("/eliminar/{cedula}")
    public ResponseEntity<String> eliminar(
            @PathVariable String cedula) {
        clienteService.eliminarCliente(cedula);
        return ResponseEntity.ok(
                "Cliente eliminado correctamente");
    }

    // Verificar existencia → usado por MS-Ventas
    @GetMapping("/existe/{cedula}")
    public ResponseEntity<Boolean> existe(
            @PathVariable String cedula) {
        return ResponseEntity.ok(
                clienteService.existePorCedula(cedula));
    }
}