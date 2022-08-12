package dev.as.curso.udemy.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.as.curso.udemy.entities.Product;
import dev.as.curso.udemy.exceptions.ResourceNotFoundException;
import dev.as.curso.udemy.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		
		return repository.findAll();
	}
	
	public Product findById(Long id) throws NoSuchElementException {
		
		Optional<Product> product = repository.findById(id);
		
		return product.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product create(Product product) {
		
		return repository.save(product);
		
	}
	public Product update(Product product, Long  id){
		product.setId(id);
		return repository.save(product);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}

