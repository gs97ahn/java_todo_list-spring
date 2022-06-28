package todolist.todolistspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todolist.todolistspring.dto.UserCreateRequest;
import todolist.todolistspring.dto.UserUpdateRequest;
import todolist.todolistspring.entity.User;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Test
    public void signUp() {
        // given
        UserCreateRequest user = new UserCreateRequest();
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setGender('M');
        user.setBirthdate(LocalDate.parse("1997-01-01"));

        // when
        Long savedUserId = userService.signUp(user);

        // then
        User foundUser = userService.findOne(savedUserId);
        assertThat(user.getName()).isEqualTo(foundUser.getName());
    }

    @Test
    public void duplicateUserException() {
        // given
        UserCreateRequest user1 = new UserCreateRequest();
        user1.setName("Test");
        user1.setEmail("test@test.com");
        user1.setGender('M');
        user1.setBirthdate(LocalDate.parse("1997-01-01"));

        UserCreateRequest user2 = new UserCreateRequest();
        user2.setName("Test");
        user2.setEmail("test@test.com");
        user2.setGender('M');
        user2.setBirthdate(LocalDate.parse("1997-01-01"));

        // when
        userService.signUp(user1);
        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> userService.signUp(user2));
        assertThat(e.getMessage()).isEqualTo("Existing user");
    }

    @Test
    void update() {
        // given
        UserCreateRequest user = new UserCreateRequest();
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setGender('M');
        user.setBirthdate(LocalDate.parse("1997-01-01"));
        Long savedUserId = userService.signUp(user);

        // when
        UserUpdateRequest updatingUser = new UserUpdateRequest();
        updatingUser.setName("Update Test");
        updatingUser.setGender(user.getGender());
        updatingUser.setBirthdate(user.getBirthdate());
        userService.update(savedUserId, updatingUser);

        // then
        assertThat(user.getEmail()).isEqualTo(userService.findOne(savedUserId).getEmail());
    }

    @Test
    void nonExistingUserUpdateException() {
        // given
        UserUpdateRequest user = new UserUpdateRequest();
        user.setName("Test Name");
        user.setGender('M');
        user.setBirthdate(LocalDate.parse("1997-01-01"));

        // when
        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> userService.update(0L, user));
        assertThat(e.getMessage()).isEqualTo("Non-existing user");
    }
}
