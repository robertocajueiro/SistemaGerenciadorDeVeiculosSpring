package com.fuctura.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fuctura.projeto.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
