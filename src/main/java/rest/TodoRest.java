package rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entity.Todo;
import service.TodoService;

@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoRest {

	@Inject
	TodoService todoService;
	
	@Path("new")
	@POST
	public Response createTodo(Todo todo) {
		// api/v1/todo/new
		
		todoService.createTodo(todo);
		
		return Response.ok(todo).build();
	}
	
	@Path("update")
	@PUT
	public Response updateTodo(Todo todo) {
		
		todoService.updateTodo(todo);
		
		return Response.ok(todo).build();
	}
	
	@Path("id")
	@GET
	public Todo getTodo(@PathParam("id") Long ID) {
		return todoService.findTodoById(ID);
	}
	
	@Path("list")
	@GET
	public List<Todo> getTodos(){
		return todoService.getTodos();
	}
	
	// here the user posts the JSON body with the id and this service automatically sets it as complete
	@Path("status")
	@POST
	public Response markAsComplete(@QueryParam("id") Long id) {
		Todo todo = todoService.findTodoById(id);
		todo.setCompleted(true);
		
		// be safe: don't forget to commit changes (not strictly needed)
		todoService.updateTodo(todo);
		
		return Response.ok(todo).build();
	}
}
