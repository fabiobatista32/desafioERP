package com.desafio.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.desafio.model.ItemPedido;

public interface ItemPedidoService {
	
	ItemPedido create(ItemPedido itemPedido);
    
	ItemPedido update(ItemPedido itemPedido);
	
	void delete(ItemPedido itemPedido);
        
	ItemPedido findById(String id);
	
	List<ItemPedido> findAll();
    
    Page<ItemPedido> findAll(int page, int size);

}
