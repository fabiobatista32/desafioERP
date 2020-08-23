package com.desafio.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
	
	Optional<Pedido> findById(UUID id);
	
	Page<Pedido> findAll(Pageable pageable);
	
}
