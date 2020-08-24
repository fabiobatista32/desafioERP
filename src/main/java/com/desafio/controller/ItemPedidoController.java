package com.desafio.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dto.Response;
import com.desafio.exception.CustomException;
import com.desafio.model.ItemPedido;
import com.desafio.service.ItemPedidoService;

@RestController
@RequestMapping("/api/item-pedido")
@CrossOrigin(origins = "*")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService service;

	@GetMapping
	public ResponseEntity<Response<List<ItemPedido>>> findAll() {
		Response<List<ItemPedido>> response = new Response<>();
		response.setData(service.findAll());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value="{page}/{size}")
    public ResponseEntity<Response<Page<ItemPedido>>> findAll(
			@PathVariable int page, 
			@PathVariable int size){		
		Response<Page<ItemPedido>> response = new Response<>();
		response.setData(service.findAll(page, size));
    	return ResponseEntity.ok(response);    	
    }

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<ItemPedido>> findById(@PathVariable("id") UUID id) {
		Response<ItemPedido> response = new Response<>();
		response.setData(service.findById(id));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<ItemPedido>> create(@RequestHeader(value = "Authorization", required = false) String authorization,
			@RequestBody ItemPedido itemPedido) {
		Response<ItemPedido> response = new Response<>();
		response.setData(service.create(itemPedido));
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<Response<ItemPedido>> update(@RequestHeader(value = "Authorization", required = false) String authorization,
			@RequestBody ItemPedido itemPedido) {
		Response<ItemPedido> response = new Response<>();
		response.setData(service.update(itemPedido));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") UUID id) {
		ItemPedido itemPedido = service.findById(id);
		if(itemPedido != null)
			service.delete(itemPedido);
		else
			throw new CustomException("ItemPedido não encontrado", HttpStatus.ACCEPTED);
	}

}
