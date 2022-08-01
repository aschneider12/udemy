package dev.as.curso.udemy.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
			
			//tratar exceção
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
		
	}
	
	
	
}
