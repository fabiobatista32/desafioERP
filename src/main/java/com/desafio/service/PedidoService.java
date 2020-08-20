package com.desafio.service;

import org.springframework.data.domain.Page;

import com.desafio.model.Pedido;

public interface PedidoService {

	Pedido create(Pedido pedido);
    
	Pedido update(Pedido pedido);
        
	Pedido findById(String id);
    
    Page<Pedido> findAll(int page, int size);
    
}
