package com.teste.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teste.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
