package com.desafio.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio.enums.TipoItemEnum;
import com.desafio.model.ItemPedido;
import com.desafio.model.Pedido;
import com.desafio.repository.PedidoRepository;
import com.desafio.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public Pedido create(Pedido pedido) {
		
		List<ItemPedido> itensProdutos = pedido.getItens().stream()
        		.filter(item -> item.getItem().getTipo().equals(TipoItemEnum.PRODUTO))
        		.collect(Collectors.toList());
        	        
    	BigDecimal valorProdutos = itensProdutos.stream().map(item->item.getValorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
    	
    	List<ItemPedido> itensServicos = pedido.getItens().stream()
        		.filter(item -> item.getItem().getTipo().equals(TipoItemEnum.SERVICO))
        		.collect(Collectors.toList());
        	        
    	BigDecimal valorServicos = itensServicos.stream().map(item->item.getValorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
		
		if(pedido.getValorDesconto().compareTo(BigDecimal.ZERO) > 0) {
	        valorProdutos = valorProdutos.subtract(valorProdutos.multiply(pedido.getValorDesconto())); 	
	    }
		
    	pedido.setData(LocalDate.now());
    	pedido.setHora(LocalTime.now());
    	pedido.setValorTotal(valorProdutos.add(valorServicos));
    	
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido update(Pedido pedido) {
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
