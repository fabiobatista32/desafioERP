package com.desafio.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio.model.ItemPedido;
import com.desafio.repository.itemPedidoRepository;
import com.desafio.service.ItemPedidoService;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {
	
	@Autowired
	private itemPedidoRepository itemPedidoRepository;

	@Override
	public ItemPedido create(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	@Override
	public ItemPedido update(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	@Override
	public ItemPedido findById(String id) {
		Optional<ItemPedido> optional = itemPedidoRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Page<ItemPedido> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return itemPedidoRepository.findAll(pageable);
	}

}
