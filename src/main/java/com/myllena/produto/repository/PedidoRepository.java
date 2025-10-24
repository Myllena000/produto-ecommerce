package com.myllena.produto.repository;

import com.myllena.produto.entity.PedidoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoEntity, Long> {
}
