package entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String task;
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
