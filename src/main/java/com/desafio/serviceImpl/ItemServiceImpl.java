package com.desafio.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafio.exception.CustomException;
import com.desafio.model.Item;
import com.desafio.model.ItemPedido;
import com.desafio.repository.ItemRepository;
import com.desafio.repository.itemPedidoRepository;
import com.desafio.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private itemPedidoRepository itemPedidoRepository;

	@Override
	public Item create(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item update(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item findById(UUID id) {
		Optional<Item> optional = itemRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}		
	
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Page<Item> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return itemRepository.findAll(pageable);
	}

	@Override
	public void delete(Item item) {
		List<ItemPedido> list = itemPedidoRepository.findByItemUuid(item.getUuid());
		if(list.isEmpty())
			itemRepository.delete(item);
		else
			throw new CustomException("Item associado a um pedido", HttpStatus.ACCEPTED);
	}

}
