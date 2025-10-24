package com.myllena.produto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myllena.produto.controller.validator.PhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
public class ClienteEntity extends DefaultEntity {

    @NotBlank(message = "O cliente deve possuir um nome válido")
    private String nome;
    @PhoneNumber
    private String telefone;
    @Email
    @Column(unique = true)
    private String email;
    @JsonIgnore
    @OneToMany(orphanRemoval = true)
    private Set<PedidoEntity> pedidos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PedidoEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
    }
}
