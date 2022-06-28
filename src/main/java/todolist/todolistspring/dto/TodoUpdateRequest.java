package todolist.todolistspring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TodoUpdateRequest {
    @NotBlank(message = "Content field is empty")
    private String content;

    @NotNull(message = "IsComplete field is null")
    private Boolean isComplete;

    public TodoUpdateRequest(TodoUpdateRequest request) {
        this.content = request.content;
        this.isComplete = request.isComplete;
    }
}
