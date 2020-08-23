package com.desafio.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.desafio.model.Pedido;

public interface PedidoService {

	Pedido create(Pedido pedido);
    
	Pedido update(Pedido pedido);
	
	void delete(Pedido pedido);
        
	Pedido findById(UUID id);
	
    List<Pedido> findAll();
    
    Page<Pedido> findAll(int page, int size);
    
}
