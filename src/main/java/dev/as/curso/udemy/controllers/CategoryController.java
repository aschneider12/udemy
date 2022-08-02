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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.as.curso.udemy.entities.Category;
import dev.as.curso.udemy.services.CategoryService;

@Controller
@RequestMapping({"/categories"})
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){

		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<?> findById(@PathVariable Long id){

		try { 
			
			Category category = service.findById(id);
			return ResponseEntity.ok().body(category);
			
		} catch (NoSuchElementException e) {
			//ao inves de ver la no service se ele existe, captura a exeção aqui e trata pra tela
			//tratar exceção
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category){
		
		category = service.create(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).body(category);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id){
		
		category = service.update(category, id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
				
	}
	
	
}
