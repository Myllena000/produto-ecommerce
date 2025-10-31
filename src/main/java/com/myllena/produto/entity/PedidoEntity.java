package com.myllena.produto.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class PedidoEntity extends DefaultEntity {

    private UUID numeroPedido = UUID.randomUUID();
    private LocalDate dataEmissao = LocalDate.now();
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity cliente;

    @OneToMany
    @JoinColumn(name = "prod_id") // ele cria uma coluna na tabela PRODUTO_ENTITY
    private Set<ProdutoEntity> produtos = new HashSet<>();

    public UUID getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }
}
