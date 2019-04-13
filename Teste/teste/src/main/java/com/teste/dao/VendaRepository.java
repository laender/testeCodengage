package com.teste.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teste.model.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long> {

}
