package com.desafio.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio.enums.SituacaoPedidoEnum;
import com.desafio.enums.TipoItemEnum;
import com.desafio.model.Item;
import com.desafio.model.ItemPedido;
import com.desafio.model.Pedido;
import com.desafio.repository.ItemRepository;
import com.desafio.repository.PedidoRepository;
import com.desafio.repository.itemPedidoRepository;
import com.desafio.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private itemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Pedido create(Pedido pedido) {
		
		if(pedido.getItens() != null) {
			List<ItemPedido> list = new ArrayList<>();
			pedido.getItens().forEach(element ->{
				ItemPedido itemPedido = null;
				if(element.getUuid() == null) {
					Item item = itemRepository.getOne(element.getItem().getUuid());
					itemPedido = new ItemPedido(item, pedido, null, element.getQuantidade());
					itemPedido.setValorUnitario(item.getValorUnitario());
				}else {
					Optional<ItemPedido> optional = itemPedidoRepository.findById(element.getUuid());
					itemPedido = optional.isPresent() ? optional.get() : null;
				}
				
				if(itemPedido != null)
					list.add(itemPedido);
				
			});
			
			pedido.setItens(list);
			
		}
		
		List<ItemPedido> listItemAtivos = pedido.getItens().stream().filter(item -> item.getItem().getAtivo())
				.collect(Collectors.toList());
		
		pedido.setItens(listItemAtivos);
		
		List<ItemPedido> itensProdutos = pedido.getItens().stream()
        		.filter(item -> item.getItem().getTipo().equals(TipoItemEnum.PRODUTO))
        		.collect(Collectors.toList());
        	        
    	BigDecimal valorProdutos = itensProdutos.stream().map(item->item.getValorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
    	
    	List<ItemPedido> itensServicos = pedido.getItens().stream()
        		.filter(item -> item.getItem().getTipo().equals(TipoItemEnum.SERVICO))
        		.collect(Collectors.toList());
        	        
    	BigDecimal valorServicos = itensServicos.stream().map(item->item.getValorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
    	
		if(pedido.getSituacao().equals(SituacaoPedidoEnum.ABERTO) && pedido.getValorDesconto().compareTo(BigDecimal.ZERO) > 0) {
	        valorProdutos = valorProdutos.subtract(valorProdutos.multiply(pedido.getValorDesconto())); 	
	    }
				
    	pedido.setData(LocalDate.now());
    	pedido.setHora(LocalTime.now());
    	pedido.setValorTotal(valorProdutos.add(valorServicos));
    	
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido update(Pedido pedido) {
		
		if(pedido.getItens() != null) {
			List<ItemPedido> list = new ArrayList<>();
			pedido.getItens().forEach(element ->{
				ItemPedido itemPedido = null;
				if(element.getUuid() == null) {
					Item item = itemRepository.getOne(element.getItem().getUuid());
					
					List<ItemPedido> listItensPedidos = itemPedidoRepository.findByItemUuid(item.getUuid());
					if(!listItensPedidos.isEmpty()) {
						
						itemPedido = listItensPedidos.stream()
								.filter(i -> i.getItem().getUuid() == item.getUuid() && i.getPedido().getUuid() == pedido.getUuid())
								.findFirst()
								.orElse(null);
						
						if(itemPedido == null) {
							itemPedido = new ItemPedido(item, pedido, null, element.getQuantidade());
							itemPedido.setValorUnitario(item.getValorUnitario());
						}
						
					}else{
						itemPedido = new ItemPedido(item, pedido, null, element.getQuantidade());
						itemPedido.setValorUnitario(item.getValorUnitario());
					}
					
				}else {
					Optional<ItemPedido> optional = itemPedidoRepository.findById(element.getUuid());
					itemPedido = optional.isPresent() ? optional.get() : null;
				}
				
				if(itemPedido != null)
					list.add(itemPedido);
			});
			
			pedido.setItens(list);
			
		}
		
		List<ItemPedido> listItemAtivos = pedido.getItens().stream().filter(item -> item.getItem().getAtivo())
				.collect(Collectors.toList());
		
		pedido.setItens(listItemAtivos);
		
		List<ItemPedido> itensProdutos = pedido.getItens().stream()
        		.filter(item -> item.getItem().getTipo().equals(TipoItemEnum.PRODUTO))
        		.collect(Collectors.toList());
        	        
    	BigDecimal valorProdutos = itensProdutos.stream().map(item->item.getValorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
    	
    	List<ItemPedido> itensServicos = pedido.getItens().stream()
        		.filter(item -> item.getItem().getTipo().equals(TipoItemEnum.SERVICO))
        		.collect(Collectors.toList());
        	        
    	BigDecimal valorServicos = itensServicos.stream().map(item->item.getValorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
    	
		if(pedido.getSituacao().equals(SituacaoPedidoEnum.ABERTO) && pedido.getValorDesconto().compareTo(BigDecimal.ZERO) > 0) {
	        valorProdutos = valorProdutos.subtract(valorProdutos.multiply(pedido.getValorDesconto())); 	
	    }
	
    	pedido.setValorTotal(valorProdutos.add(valorServicos));
    			
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido findById(UUID id) {
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
