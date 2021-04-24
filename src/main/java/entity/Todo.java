package entity;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "The task must be set")
	@Size(min = 10, message = "Task must be at least 10 chars long")
	private String task;
	
	@NotNull(message = "Due date must be set")
	@FutureOrPresent(message = "Cannot set due date in the past")
	@JsonbDateFormat(value = "yyyy-MM-dd")
	private LocalDate dueDate;
	
	private boolean isCompleted;
	private LocalDate dateCompleted;
	private LocalDate dateCreated;
	
	// run before persisting POJOs to file
	@PrePersist
	private void init() {
		setDateCreated(LocalDate.now());
	}
}
