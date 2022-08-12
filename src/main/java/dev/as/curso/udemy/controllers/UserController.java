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

import dev.as.curso.udemy.entities.User;
import dev.as.curso.udemy.services.UserService;

@Controller
@RequestMapping({"/users"})
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){

		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<?> findById(@PathVariable Long id){

		try { 
			
			User user = service.findById(id);
			return ResponseEntity.ok().body(user);
			
		} catch (NoSuchElementException e) {
			//ao inves de ver la no service se ele existe, captura a exeção aqui e trata pra tela
			//tratar exceção
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user){
		
		user = service.create(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).body(user);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable("id") Long id){
		
		user = service.update(user, id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		System.out.println(id);
		service.delete(id);
		return ResponseEntity.noContent().build();
				
	}
	
	
}
