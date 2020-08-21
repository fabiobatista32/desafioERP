package com.desafio.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.desafio.model.Item;

public interface ItemService {
	
	Item create(Item item);
    
	Item update(Item item);
	
	void delete(Item item);
        
	Item findById(String id);
	
	List<Item> findAll();
    
    Page<Item> findAll(int page, int size);

}
