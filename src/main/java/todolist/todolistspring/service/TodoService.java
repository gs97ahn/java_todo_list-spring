package todolist.todolistspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.todolistspring.dto.TodoCreateRequest;
import todolist.todolistspring.entity.Todo;
import todolist.todolistspring.entity.User;
import todolist.todolistspring.respository.TodoRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo create(User user, TodoCreateRequest request) {
        Todo todo = new Todo(user, request.getContent(), request.getIsComplete());
        return todoRepository.save(todo);
    }

    @Transactional
    public Long updateContent(Long id, String content) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            Todo updatingContent = todo.get();
            updatingContent.updateContent(content);
            return updatingContent.getId();
        } else throw new IllegalStateException("Non-existing todo");
    }

    @Transactional
    public Long updateIsComplete(Long id, Boolean isComplete) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            Todo updatingIsComplete = todo.get();
            updatingIsComplete.updateIsComplete(isComplete);
            return updatingIsComplete.getId();
        } else throw new IllegalStateException("Non-existing todo");
    }

    @Transactional
    public Long delete(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            Todo deletingTodo = todo.get();
            Long deletingTodoId = deletingTodo.getId();
            todoRepository.delete(deletingTodo);
            return deletingTodoId;
        } else throw new IllegalStateException("Non-existing todo");
    }

    @Transactional
    public User findUser(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) return todo.get().getUser();
        else throw new IllegalStateException("Non-existing todo");
    }

    @Transactional
    public Todo findOne(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) return todo.get();
        else throw new IllegalStateException("Non-existing todo");
    }
}
