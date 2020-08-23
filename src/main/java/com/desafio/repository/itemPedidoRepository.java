package com.desafio.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.model.ItemPedido;

public interface itemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
	
	Optional<ItemPedido> findById(UUID id);
	
	Page<ItemPedido> findAll(Pageable pageable);

}
