package com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.desafio.model.Pedido;
import com.desafio.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {
	
	@Autowired
	private PedidoService service;

	@GetMapping
	public ResponseEntity<Response<List<Pedido>>> findAll() {
		Response<List<Pedido>> response = new Response<>();
		response.setData(service.findAll());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value="{page}/{size}")
    public ResponseEntity<Response<Page<Pedido>>> findAll(
			@PathVariable int page, 
			@PathVariable int size){		
		Response<Page<Pedido>> response = new Response<>();
		response.setData(service.findAll(page, size));
    	return ResponseEntity.ok(response);    	
    }

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Pedido>> findById(@PathVariable("id") String id) {
		Response<Pedido> response = new Response<>();
		response.setData(service.findById(id));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Pedido>> create(@RequestHeader(value = "Authorization", required = false) String authorization,
			@RequestBody Pedido pedido) {
		Response<Pedido> response = new Response<>();
		response.setData(service.create(pedido));
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<Response<Pedido>> update(@RequestHeader(value = "Authorization", required = false) String authorization,
			@RequestBody Pedido pedido) {
		Response<Pedido> response = new Response<>();
		response.setData(service.update(pedido));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		Pedido pedido = service.findById(id);
		if(pedido != null)
			service.delete(pedido);
	}

}
