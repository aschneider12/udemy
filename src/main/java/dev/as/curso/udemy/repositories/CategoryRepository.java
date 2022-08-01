package dev.as.curso.udemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.as.curso.udemy.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
