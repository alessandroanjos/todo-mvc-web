package com.alessandro.todomvc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alessandro.todomvc.models.Todo;
import com.alessandro.todomvc.repository.TodoRepository;

@RestController
@RequestMapping(value="/api")
public class TodoResource {

	@Autowired
	TodoRepository todoRepository;
	
	@GetMapping("/todos")
	public List<Todo> listAllTodo(){
		return todoRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
	}
	
	@GetMapping("/todo/{id}")
	public Todo getTodoById(@PathVariable(value="id") long id) {
		return todoRepository.findById(id);
	}
	
	@PostMapping("/todo")
	public Todo saveTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}
	
	@DeleteMapping("/todo")
	public void deleteTodo(@RequestBody Todo todo) {
		todoRepository.delete(todo);
	}
	
	@PutMapping("/todo")
	public Todo updateTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@PostMapping("/todo/complete/{id}")
	public void completeTodoById(@PathVariable(value="id") long id) {
		Todo todo = todoRepository.findById(id);
		todo.setComplete(true);
		todoRepository.save(todo);
	}
	
	@PostMapping("/todo/notComplete/{id}")
	public void notCompleteTodoById(@PathVariable(value="id") long id) {
		Todo todo = todoRepository.findById(id);
		todo.setComplete(false);
		todoRepository.save(todo);
	}
	
	@PostMapping("/todo/delete/{id}")
	public void deleteTodoById(@PathVariable(value="id") long id) {
		Todo todo = todoRepository.findById(id);
		todoRepository.delete(todo);
	}
	
}
