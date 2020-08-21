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
import com.desafio.model.Item;
import com.desafio.service.ItemService;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*")
public class ItemController {
	
	@Autowired
	private ItemService service;

	@GetMapping
	public ResponseEntity<Response<List<Item>>> findAll() {
		Response<List<Item>> response = new Response<>();
		response.setData(service.findAll());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value="{page}/{size}")
    public ResponseEntity<Response<Page<Item>>> findAll(
			@PathVariable int page, 
			@PathVariable int size){		
		Response<Page<Item>> response = new Response<>();
		response.setData(service.findAll(page, size));
    	return ResponseEntity.ok(response);    	
    }

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Item>> findById(@PathVariable("id") String id) {
		Response<Item> response = new Response<>();
		response.setData(service.findById(id));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Item>> create(@RequestHeader(value = "Authorization", required = false) String authorization,
			@RequestBody Item item) {
		Response<Item> response = new Response<>();
		response.setData(service.create(item));
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<Response<Item>> update(@RequestHeader(value = "Authorization", required = false) String authorization,
			@RequestBody Item item) {
		Response<Item> response = new Response<>();
		response.setData(service.update(item));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		Item item = service.findById(id);
		if(item != null)
			service.delete(item);
	}

}
