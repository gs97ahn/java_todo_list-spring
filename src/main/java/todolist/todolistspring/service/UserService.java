package todolist.todolistspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.todolistspring.dto.UserCreateRequest;
import todolist.todolistspring.dto.UserUpdateRequest;
import todolist.todolistspring.entity.User;
import todolist.todolistspring.respository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long signUp(UserCreateRequest request) {
        User user = new User(request.getName(), request.getEmail(), request.getGender(), request.getBirthdate());
        isExistingEmail(user.getEmail());
        User createdUser = userRepository.save(user);
        return createdUser.getId();
    }

    private void isExistingEmail(String email) {
        userRepository.findByEmail(email)
                .ifPresent(m -> {
                    throw new IllegalStateException("Existing user");
                });
    }

    @Transactional
    public Long update(Long id, UserUpdateRequest request) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatingUser = user.get();
            updatingUser.updateUser(request.getName(), request.getGender(), request.getBirthdate());
            return updatingUser.getId();
        } else throw new IllegalStateException("Non-existing user");
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) return user.get();
        else throw new IllegalStateException("Non-existing user");
    }
}
