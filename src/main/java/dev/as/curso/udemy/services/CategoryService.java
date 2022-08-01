package dev.as.curso.udemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.as.curso.udemy.entities.Category;
import dev.as.curso.udemy.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	 /*
	  * optional.get()
	  * @throws NoSuchElementException if no value is present
	  */
	public Category findById(Long id){
		Optional<Category> obj = repository.findById(id);
		return obj.get();//esse get lança uma excpetion caso não tenha nada no objeto optional
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
