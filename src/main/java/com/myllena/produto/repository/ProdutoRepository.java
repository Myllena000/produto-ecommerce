package com.myllena.produto.repository;

import com.myllena.produto.controller.ProdutoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findAll();
    List<ProdutoEntity> findAllByNome(String nome);
    Optional<ProdutoEntity> findById(Long id);
}

