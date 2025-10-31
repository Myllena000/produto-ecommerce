package com.myllena.produto.repository;

import com.myllena.produto.entity.ClienteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteEntity, Long> {

    List<ClienteEntity> findAll();
    Optional<ClienteEntity> findById(Long id);
}
