package todolist.todolistspring.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.todolistspring.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
