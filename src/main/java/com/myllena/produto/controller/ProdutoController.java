package com.myllena.produto.controller;

import com.myllena.produto.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/produtos")
    public ResponseEntity<Void> registrar(@RequestBody ProdutoEntity produtoEntity) {
        produtoRepository.save(produtoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoEntity>> listarPeloNome
            (@RequestParam(required = false) String nome, ProdutoEntity produtoEntity) {

        if (produtoEntity.getNome() != null) {
            return ResponseEntity.ok().body(produtoRepository.findAllByNome(nome));
        }
        return ResponseEntity.ok().body(produtoRepository.findAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoEntity> listarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/produtos/{id}")
    public ResponseEntity<ProdutoEntity> alterarParteProd(@PathVariable Long id, @RequestBody ProdutoEntity produtoEntity) {
        var alterarParc = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        alterarParc.setPreco(produtoEntity.getPreco());

        if (produtoEntity.getPreco() <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoRepository.save(alterarParc));
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoEntity> alterarProd(@PathVariable Long id, @RequestBody ProdutoEntity produtoEntity) {
        var produtoAtualizar = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        produtoAtualizar.setNome(produtoEntity.getNome());
        produtoAtualizar.setDataVenc(produtoEntity.getDataVenc());
        produtoAtualizar.setPreco(produtoEntity.getPreco());

        if (produtoEntity.getPreco() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(20);
        LocalDate vencimento = produtoEntity.getDataVenc();
        if(vencimento.isBefore(dataLimite) || vencimento.isEqual(dataLimite)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(produtoRepository.save(produtoAtualizar));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id, @RequestBody ProdutoEntity produtoEntity) {
        if (produtoRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

