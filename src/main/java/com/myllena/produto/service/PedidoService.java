package com.myllena.produto.service;

import com.myllena.produto.controller.entity.PedidoRequest;
import com.myllena.produto.controller.entity.PedidoResponse;
import com.myllena.produto.controller.entity.ProdutoRequest;
import com.myllena.produto.repository.ClienteRepository;
import com.myllena.produto.repository.PedidoRepository;
import com.myllena.produto.repository.ProdutoRepository;
import com.myllena.produto.repository.entity.ItemPedidoProduto;
import com.myllena.produto.repository.entity.PedidoEntity;
import com.myllena.produto.repository.entity.ProdutoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoResponse registrarPedido(PedidoRequest novoPedido) {
        var cliente = clienteRepository.findById(novoPedido.getClienteId()).orElseThrow();
        double valorTotal = 0.0;

        // criar a entidade pedido (ainda sem itens e sem valorTotal)
        var pedidoEntity = PedidoEntity.builder()
                .cep(novoPedido.getCep())
                .cliente(cliente)
                .build();

        Set<ItemPedidoProduto> itens = new HashSet<>();

        for (ProdutoRequest produtoReq : novoPedido.getProdutos()) {
            ProdutoEntity produtoEntity = produtoRepository.findById(produtoReq.getProdutoId()).orElseThrow();

            if (produtoEntity.getQuantidadeEstoque() < produtoReq.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produtoEntity.getNome());
            }

           atualizarEstoque(produtoEntity, produtoReq.getQuantidade());

            // criar item de pedido (observação: ItemPedidoProduto não guarda quantidade neste model)
            ItemPedidoProduto item = ItemPedidoProduto.builder()
                    .pedido(pedidoEntity)
                    .produto(produtoEntity)
                    .build();

            itens.add(item);
        }

        // atribuir itens e valor total ao pedido e salvar
        pedidoEntity.setValorTotal(valorTotal);
        pedidoEntity.setItens(itens);

        PedidoEntity pedidoSalvo = pedidoRepository.save(pedidoEntity);

        return PedidoResponse.builder()
                .pedidoId(pedidoSalvo.getNumeroPedido())
                .dataEmissao(pedidoSalvo.getDataEmissao())
                .dataEntrega(pedidoSalvo.getDataEmissao().plusDays(7))
                .valorTotal(pedidoSalvo.getValorTotal())
                .cliente(pedidoSalvo.getCliente())
                .build();
    }


    private void atualizarEstoque(ProdutoEntity produto, int quantidadeVendida) {
        int novoEstoque = produto.getQuantidadeEstoque() - quantidadeVendida;
        produto.setQuantidadeEstoque(novoEstoque);
        produtoRepository.save(produto);
    }
}
