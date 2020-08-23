package com.desafio.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.desafio.model.Item;

public interface ItemService {
	
	Item create(Item item);
    
	Item update(Item item);
	
	void delete(Item item);
        
	Item findById(UUID id);
	
	List<Item> findAll();
    
    Page<Item> findAll(int page, int size);

}
