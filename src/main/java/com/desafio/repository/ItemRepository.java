package com.desafio.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.model.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {
	
	Optional<Item> findById(UUID id);
	
	Page<Item> findAll(Pageable pageable);
	
}
