package com.myllena.produto.controller;

import com.myllena.produto.entity.ClienteEntity;
import com.myllena.produto.entity.PedidoEntity;
import com.myllena.produto.repository.ClienteRepository;
import com.myllena.produto.repository.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public ClienteController(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteEntity> registrarCliente(@RequestBody @Valid ClienteEntity clienteEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteEntity));
    }

    @PostMapping("/clientes/{id}/pedidos")
    public ResponseEntity<PedidoEntity> registrarPedidoCliente(@PathVariable Long id, @RequestBody PedidoEntity pedidoEntity) {
        var cliente = clienteRepository.findById(id).get();
        pedidoEntity.setCliente(cliente);


        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedidoEntity));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteEntity>> listarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    /**
     * Corrigir
     *
     * @param id
     * @return
     */
    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteEntity> listarPorId(@PathVariable Long id) {
        var semId = clienteRepository.findById(id).isEmpty();
        if (semId) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findById(id).get());

    }

    @PatchMapping("/clientes/{id}")
    public ResponseEntity<ClienteEntity> alterarParc(@PathVariable Long id, @RequestBody ClienteEntity clienteEntity) {
        var atualizacao = clienteRepository.findById(id).get();
        atualizacao.setEmail(clienteEntity.getEmail());
        atualizacao.setTelefone(clienteEntity.getTelefone());
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(atualizacao));
    }

    @DeleteMapping("/clientes/{id}")
    ResponseEntity<ClienteEntity> deletar(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
