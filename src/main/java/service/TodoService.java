package service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import entity.Todo;

@Transactional
public class TodoService {

	@PersistenceContext
	EntityManager entityManager;
	
	public Todo createTodp(Todo todo) {
		entityManager.persist(todo);
		return todo;
	}
	
	
}
