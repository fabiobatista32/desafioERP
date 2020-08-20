package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
	
	Optional<Pedido> findById(String id);
	
	Page<Pedido> findAll(Pageable pageable);
	
}
