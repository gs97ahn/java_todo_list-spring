package todolist.todolistspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todolist.todolistspring.dto.TodoCreateRequest;
import todolist.todolistspring.dto.UserCreateRequest;
import todolist.todolistspring.entity.Todo;
import todolist.todolistspring.entity.User;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class TodoServiceIntegrationTest {

    @Autowired
    TodoService todoService;
    @Autowired
    UserService userService;

    @Test
    public void create() {
        //given
        UserCreateRequest user = new UserCreateRequest();
        user.setName("Name");
        user.setEmail("email@email.com");
        user.setGender('M');
        user.setBirthdate(LocalDate.parse("1997-01-01"));
        Long createdUserId = userService.signUp(user);
        User createdUser = userService.findOne(createdUserId);

        TodoCreateRequest todo = new TodoCreateRequest();
        todo.setContent("Content");
        todo.setIsComplete(false);

        // when
        Todo savedTodoId = todoService.create(createdUser, todo);

        // then
        Todo foundTodo = todoService.findOne(savedTodoId.getId());
        assertThat(todo.getContent()).isEqualTo(foundTodo.getContent());
    }

    @Test
    public void updateContent() {
        // given
        UserCreateRequest user = new UserCreateRequest();
        user.setName("Name");
        user.setEmail("email@email.com");
        user.setGender('M');
        user.setBirthdate(LocalDate.parse("1997-01-01"));
        Long createdUserId = userService.signUp(user);
        User createdUser = userService.findOne(createdUserId);

        TodoCreateRequest todo = new TodoCreateRequest();
        todo.setContent("Content");
        todo.setIsComplete(Boolean.FALSE);
        Todo savedTodoId = todoService.create(createdUser, todo);

        // when
        todoService.updateContent(savedTodoId.getId(), "New Content");

        // then
        assertThat(todo.getContent()).isEqualTo("New Content");
    }

    @Test
    void nonExistingTodoContentUpdateException() {
        // when
        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> todoService.updateContent(0L, "New Content"));
        assertThat(e.getMessage()).isEqualTo("Non-existing todo");
    }

    @Test
    public void updateIsComplete() {
        // given
        UserCreateRequest user = new UserCreateRequest();
        user.setName("Name");
        user.setEmail("email@email.com");
        user.setGender('M');
        user.setBirthdate(LocalDate.parse("1997-01-01"));
        Long createdUserId = userService.signUp(user);
        User createdUser = userService.findOne(createdUserId);

        TodoCreateRequest todo = new TodoCreateRequest();
        todo.setContent("Content");
        todo.setIsComplete(Boolean.FALSE);
        Todo savedTodoId = todoService.create(createdUser, todo);

        // when
        todoService.updateIsComplete(savedTodoId.getId(), Boolean.TRUE);

        // then
        assertThat(todo.getIsComplete()).isEqualTo(Boolean.TRUE);
    }

    @Test
    void nonExistingTodoIsCompleteUpdateException() {
        // when
        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> todoService.updateIsComplete(0L, Boolean.TRUE));
        assertThat(e.getMessage()).isEqualTo("Non-existing todo");
    }

    @Test
    public void delete() {
        // given
        UserCreateRequest user = new UserCreateRequest();
        user.setName("Name");
        user.setEmail("email@email.com");
        user.setGender('M');
        user.setBirthdate(LocalDate.parse("1997-01-01"));
        Long createdUserId = userService.signUp(user);
        User createdUser = userService.findOne(createdUserId);

        TodoCreateRequest todo = new TodoCreateRequest();
        todo.setContent("Content");
        todo.setIsComplete(Boolean.FALSE);
        Todo savedTodo = todoService.create(createdUser, todo);
        Long deletedTodoId = savedTodo.getId();

        // when
        assertThat(todoService.delete(deletedTodoId)).isEqualTo(deletedTodoId);
    }

    @Test
    void nonExistingTodoDeleteException() {
        // when
        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> todoService.delete(0L));
        assertThat(e.getMessage()).isEqualTo("Non-existing todo");
    }
}