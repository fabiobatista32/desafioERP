package com.desafio.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio.model.Item;
import com.desafio.repository.ItemRepository;
import com.desafio.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item create(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item update(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item findById(String id) {
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
		itemRepository.delete(item);		
	}

}
