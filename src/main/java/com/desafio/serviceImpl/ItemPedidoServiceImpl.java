package com.desafio.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio.model.Item;
import com.desafio.model.ItemPedido;
import com.desafio.repository.ItemRepository;
import com.desafio.repository.itemPedidoRepository;
import com.desafio.service.ItemPedidoService;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {
	
	@Autowired
	private itemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public ItemPedido create(ItemPedido itemPedido) {
		if(itemPedido.getItem() != null	) {
			Optional<Item> optional = itemRepository.findById(itemPedido.getItem().getUuid());
			Item item = optional.isPresent() ? optional.get() : null;
			if(item != null) {
				itemPedido.setValorUnitario(item.getValorUnitario());
				itemPedido.setItem(item);
			}
		}
		return itemPedidoRepository.save(itemPedido);
	}

	@Override
	public ItemPedido update(ItemPedido itemPedido) {
		if(itemPedido.getItem() != null	){
			Optional<Item> optional = itemRepository.findById(itemPedido.getItem().getUuid());
			Item item = optional.isPresent() ? optional.get() : null;
			if(item != null) {
				itemPedido.setValorUnitario(item.getValorUnitario());
				itemPedido.setItem(item);
			}
		}
		return itemPedidoRepository.save(itemPedido);
	}

	@Override
	public ItemPedido findById(UUID id) {
		Optional<ItemPedido> optional = itemPedidoRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Page<ItemPedido> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return itemPedidoRepository.findAll(pageable);
	}

	@Override
	public List<ItemPedido> findAll() {
		return itemPedidoRepository.findAll();
	}

	@Override
	public void delete(ItemPedido itemPedido) {
		itemPedidoRepository.delete(itemPedido);
	}

	@Override
	public List<ItemPedido> findByItemId(UUID itemUuid) {
		List<ItemPedido> list = itemPedidoRepository.findByItemUuid(itemUuid);
		return list;
	}

}
