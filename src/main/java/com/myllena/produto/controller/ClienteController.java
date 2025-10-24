package com.myllena.produto.controller;

import com.myllena.produto.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteEntity> registrarCliente(@RequestBody ClienteEntity clienteEntity) {
        if (clienteEntity.getTelefone().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteEntity));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteEntity>> listarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

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
