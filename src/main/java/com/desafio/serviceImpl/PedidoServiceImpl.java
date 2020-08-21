package com.desafio.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio.model.Pedido;
import com.desafio.repository.PedidoRepository;
import com.desafio.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public Pedido create(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido update(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido findById(String id) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Override
	public Page<Pedido> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return pedidoRepository.findAll(pageable);
	}


	@Override
	public void delete(Pedido pedido) {
		pedidoRepository.delete(pedido);		
	}

}
