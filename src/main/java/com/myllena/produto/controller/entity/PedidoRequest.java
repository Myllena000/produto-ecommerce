package com.myllena.produto.controller.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class PedidoRequest {

    @NotNull
    private Long clienteId;
    @NotBlank
    private String cep;
    @NotEmpty
    private Set<ProdutoRequest> produtos;
}
