package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
	
	Optional<Item> findById(String id);
	
	Page<Item> findAll(Pageable pageable);
	
}
