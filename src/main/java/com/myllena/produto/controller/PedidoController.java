package com.myllena.produto.controller;

import com.myllena.produto.controller.entity.PedidoRequest;
import com.myllena.produto.controller.entity.PedidoResponse;
import com.myllena.produto.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedidos")
    public ResponseEntity<PedidoResponse> registrarPedidoCliente(@RequestBody PedidoRequest novoPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.registrarPedido(novoPedido));
    }
}
