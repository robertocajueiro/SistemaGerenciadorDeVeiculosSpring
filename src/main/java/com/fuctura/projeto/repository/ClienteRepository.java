package com.fuctura.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fuctura.projeto.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
