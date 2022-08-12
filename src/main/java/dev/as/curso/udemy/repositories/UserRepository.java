package dev.as.curso.udemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.as.curso.udemy.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
