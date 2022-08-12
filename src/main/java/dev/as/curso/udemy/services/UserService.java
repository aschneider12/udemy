package dev.as.curso.udemy.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import dev.as.curso.udemy.entities.User;
import dev.as.curso.udemy.exceptions.ResourceNotFoundException;
import dev.as.curso.udemy.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@GetMapping
	public List<User> findAll(){
		
		return repository.findAll();
	}
	
	public User findById(Long id) throws NoSuchElementException {
		
		Optional<User> user = repository.findById(id);
		
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User create(User user) {
		
		return repository.save(user);
		
	}
	public User update(User user, Long  id){
		user.setId(id);
		return repository.save(user);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}

