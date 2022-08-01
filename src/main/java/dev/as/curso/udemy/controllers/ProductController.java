package dev.as.curso.udemy.controllers;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.as.curso.udemy.entities.Product;
import dev.as.curso.udemy.services.ProductService;

@Controller
@RequestMapping({"/products"})
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){

		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<?> findById(@PathVariable Long id){

		try { 
			
			Product product = service.findById(id);
			return ResponseEntity.ok().body(product);
			
		} catch (NoSuchElementException e) {
			//ao inves de ver la no service se ele existe, captura a exeção aqui e trata pra tela
			//tratar exceção
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product){
		
		product = service.create(product);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		
		return ResponseEntity.created(uri).body(product);
		
	}
	
	@RequestMapping(value = {"/id"}, method = RequestMethod.PUT)
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable("id") Long id){
		
		product = service.update(product, id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping(value = {"/id"})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
				
	}
	
	
}
