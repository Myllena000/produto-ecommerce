package com.myllena.produto.repository;

import com.myllena.produto.repository.entity.ProdutoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findAll();
    List<ProdutoEntity> findAllByNome(String nome);
    Optional<ProdutoEntity> findById(Long id);
    Set<ProdutoEntity> findAllById(Iterable<Long> ids);
}

