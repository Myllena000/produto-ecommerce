package com.myllena.produto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class PedidoEntity extends DefaultEntity {

    private UUID numeroPedido = UUID.randomUUID();
    private LocalDate dataEmissao = LocalDate.now();
    private String cep;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity cliente;

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
}
