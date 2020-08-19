package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.ItemPedido;

public interface itemPedidoRepository extends JpaRepository<ItemPedido, String> {
	
	Optional<ItemPedido> findById(String id);
	
	Page<ItemPedido> findAll(Pageable pageable);

}
