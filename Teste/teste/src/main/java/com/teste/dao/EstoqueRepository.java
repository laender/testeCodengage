package com.teste.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teste.model.Estoque;

@Repository
public interface EstoqueRepository extends CrudRepository<Estoque, Long> {

}
