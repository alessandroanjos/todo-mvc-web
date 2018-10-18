package com.alessandro.todomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alessandro.todomvc.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	/**
	 * Encontrar um Todo a partir do seu id
	 * @param id
	 * @return Todo
	 */
	Todo findById(long id);
}
