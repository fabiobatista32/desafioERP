package com.desafio.service;

import org.springframework.data.domain.Page;

import com.desafio.entity.Item;

public interface ItemService {
	
	Item create(Item item);
    
	Item update(Item item);
        
	Item findById(String id);
    
    Page<Item> findAll(int page, int size);

}
