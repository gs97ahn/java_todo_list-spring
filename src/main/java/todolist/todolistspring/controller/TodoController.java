package todolist.todolistspring.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todolist.todolistspring.dto.TodoCreateRequest;
import todolist.todolistspring.dto.TodoFindUserResponse;
import todolist.todolistspring.entity.Todo;
import todolist.todolistspring.entity.User;
import todolist.todolistspring.service.TodoService;
import todolist.todolistspring.service.UserService;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

    /**
     * Todo Create
     * @param userId user's id in Long
     * @param request user's requesting information
     * @return created todo's id in Long
     */
    @ApiOperation(value = "Todo Create")
    @PostMapping("/users/{userId}/todo")
    public Long create(@PathVariable("userId") Long userId, TodoCreateRequest request) {
        User user = userService.findOne(userId);
        Todo createdTodo = todoService.create(user, request);
        return createdTodo.getId();
    }

    /**
     * Todo update content
     * @param todoId requesting todo's id
     * @param content todo's requesting information
     * @return updated todo's id in Long
     */
    @ApiOperation(value = "Todo Content Update")
    @PatchMapping("/todos/{todoId}/content")
    public Long updateContent(@PathVariable("todoId") Long todoId, String content) {
        Todo todo = todoService.findOne(todoId);
        return todoService.updateContent(todoId, content);
    }

    /**
     * Todo update isComplete
     * @param todoId requesting todo's id
     * @param isComplete todo's requesting information
     * @return updated todo's id in Long
     */
    @ApiOperation(value = "Todo Update")
    @PatchMapping("/todos/{todoId}/isComplete")
    public Long updateIsComplete(@PathVariable("todoId") Long todoId, Boolean isComplete) {
        Todo todo = todoService.findOne(todoId);
        return todoService.updateIsComplete(todoId, isComplete);
    }

    /**
     * Delete Todo
     * @param todoId requesting todo's id
     * @return deleted todo's id in Long
     */
    @ApiOperation(value = "Todo Delete")
    @DeleteMapping("/todos/{todoId}")
    public Long delete(@PathVariable("todoId") Long todoId) {
        return todoService.delete(todoId);
    }

    /**
     * Find a Todo with the matching User
     * @param todoId requesting todo's id
     * @return found todo's information with the user information
     */
    @ApiOperation(value = "Find a Todo with the User")
    @GetMapping("/todos/{todoId}")
    public TodoFindUserResponse findOneWithUser(@PathVariable("todoId") Long todoId) {
        return new TodoFindUserResponse(todoService.findOne(todoId));
    }
}
