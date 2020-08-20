package com.desafio.service;

import org.springframework.data.domain.Page;

import com.desafio.model.ItemPedido;

public interface ItemPedidoService {
	
	ItemPedido create(ItemPedido itemPedido);
    
	ItemPedido update(ItemPedido itemPedido);
        
	ItemPedido findById(String id);
    
    Page<ItemPedido> findAll(int page, int size);

}
