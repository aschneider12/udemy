package dev.as.curso.udemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.as.curso.udemy.entities.Category;
import dev.as.curso.udemy.exceptions.ResourceNotFoundException;
import dev.as.curso.udemy.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	 /*
	  * optional.get() retorna NoSuchElementException if no value is present
	  */
	public Category findById(Long id){
		Optional<Category> category = repository.findById(id);
		
		/**
			expressão lambda do java, o metódo normal pede uma função caso não encontre o objeto
			no caso a função é enviada diretamente para -> que retorna o newww
		 */		
		return category.orElseThrow(() -> new ResourceNotFoundException(id));
		
	}
	
	public Category create(Category category) {
		
		return repository.save(category);
		
	}
	public Category update(Category category, Long  id){
		category.setId(id);
		return repository.save(category);
		
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
