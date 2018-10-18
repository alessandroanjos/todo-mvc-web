package com.alessandro.todomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alessandro.todomvc.models.Todo;
import com.alessandro.todomvc.repository.TodoRepository;

@Controller
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView index(Model model) {
		model.addAttribute("todo", new Todo());
		ModelAndView mv = new ModelAndView("index");
		Iterable<Todo> todos = todoRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
		mv.addObject("todos", todos);
		return mv;
	}
	
	@RequestMapping(value = "/todo", method=RequestMethod.POST)
	public String saveFormTodo(Todo todo) {
		todoRepository.save(todo);
		return "redirect:/";
	}
	
	
	@RequestMapping(value ="/deleteTodo/{id}")
	public String delete(@PathVariable("id") Long id) {
		Todo todo = todoRepository.findById(id).get();
		todoRepository.delete(todo);
		return "redirect:/";
	}
	
	@RequestMapping(value ="/changeStatusTodo",  method=RequestMethod.POST)
	@ResponseBody
	public String changeStatus(@RequestParam Long id) {
		Todo todo = todoRepository.findById(id).get();
		if(todo.isComplete() == true) {
			todo.setComplete(false);
		 } else {
			 todo.setComplete(true);
		  }
		todoRepository.save(todo);
		return "redirect:/";
	}
}
