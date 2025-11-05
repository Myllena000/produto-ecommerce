package com.myllena.produto.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoRequest {

    private Long produtoId;
    private int quantidade;
}
