package com.myllena.produto.controller.entity;

import com.myllena.produto.repository.entity.ClienteEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class PedidoResponse {

    private UUID pedidoId;
    private LocalDate dataEmissao;
    private LocalDate dataEntrega;
    private double valorTotal;
    private ClienteEntity cliente;


}
