package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import entity.Todo;

@Transactional
public class TodoService {

	@PersistenceContext
	EntityManager entityManager;
	
	public Todo createTodo(Todo todo) {
		entityManager.persist(todo);
		return todo;
	}
	
	public Todo updateTodo(Todo todo) {
		entityManager.merge(todo);
		return todo;
	}
	
	public Todo findTodoById(Long id) {
		return entityManager.find(Todo.class, id);
	}
	
	public List<Todo> getTodos(){
		return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
	}
}
