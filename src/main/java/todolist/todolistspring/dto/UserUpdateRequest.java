package todolist.todolistspring.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    @ApiModelProperty(example = "Name")
    @NotBlank(message = "Name field is empty")
    private String name;
    @ApiModelProperty(example = "M")
    @NotNull(message = "Gender field is empty")
    private char gender;
    @ApiModelProperty(example = "2000-01-01")
    @NotBlank(message = "Birthdate field is empty")
    private LocalDate birthdate;

    public UserUpdateRequest(UserUpdateRequest request) {
        this.name = request.name;
        this.gender = request.gender;
        this.birthdate = request.birthdate;
    }
}
