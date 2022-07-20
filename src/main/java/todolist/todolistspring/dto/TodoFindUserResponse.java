package todolist.todolistspring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.todolistspring.entity.Todo;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TodoFindUserResponse {
    private Long userId;
    private String name;
    private String email;
    private Character gender;
    private LocalDate birthdate;
    private LocalDateTime userCreatedDate;
    private LocalDateTime userLastModifiedDate;

    private Long todoId;
    private String content;
    private Boolean isComplete;
    private LocalDateTime todoCreatedDate;
    private LocalDateTime todoLastModifiedDate;

    public TodoFindUserResponse(@NotNull(message = "Todo does not exist") Todo todo) {
        this.userId = todo.getUser().getId();
        this.name = todo.getUser().getName();
        this.email = todo.getUser().getEmail();
        this.gender = todo.getUser().getGender();
        this.birthdate = todo.getUser().getBirthdate();
        this.userCreatedDate = todo.getUser().getCreatedDate();
        this.userLastModifiedDate = todo.getUser().getLastModifiedDate();

        this.todoId = todo.getId();
        this.content = todo.getContent();
        this.isComplete = todo.getIsComplete();
        this.todoCreatedDate = todo.getCreatedDate();
        this.todoLastModifiedDate = todo.getLastModifiedDate();

    }
}