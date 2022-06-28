package todolist.todolistspring.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todolist.todolistspring.dto.UserCreateRequest;
import todolist.todolistspring.dto.UserUpdateRequest;
import todolist.todolistspring.entity.User;
import todolist.todolistspring.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * User Create
     * @param request user's requesting information
     * @return created user's id in Long
     */
    @ApiOperation(value = "User Create")
    @PostMapping(value = "/users")
    public Long create(@RequestBody @Valid UserCreateRequest request) {
        return userService.signUp(request);
    }

    /**
     * User Update Information
     * @param userId requesting user's id
     * @param request user's requesting information
     * @return updated user's id in Long
     */
    @ApiOperation(value = "User Update")
    @PatchMapping("/users/{userId}")
    public Long update(@PathVariable("userId") Long userId, @RequestBody @Valid UserUpdateRequest request) {
        return userService.update(userId, request);
    }

    /**
     * Find All User
     * @return all user
     */
    @ApiOperation(value = "Find All User")
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Find a User
     * @param userId target user's id
     * @return found user in User
     */
    @ApiOperation(value = "Find a User")
    @GetMapping("/users/{userId}")
    public User findOne(@PathVariable("userId") Long userId) {
        return userService.findOne(userId);
    }
}
