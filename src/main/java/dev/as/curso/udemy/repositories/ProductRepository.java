package dev.as.curso.udemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.as.curso.udemy.entities.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
