package todolist.todolistspring.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TodoCreateRequest {
    @ApiModelProperty(example = "Content")
    @NotBlank(message = "Content field is empty")
    private String content;

    @ApiModelProperty(example = "false")
    @NotNull(message = "IsComplete field is null")
    private Boolean isComplete;

    public TodoCreateRequest(TodoCreateRequest request) {
        this.content = request.content;
        this.isComplete = request.isComplete;
    }
}
