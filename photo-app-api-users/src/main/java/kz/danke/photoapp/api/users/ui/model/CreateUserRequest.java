package kz.danke.photoapp.api.users.ui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotNull(message = "First name cannot be null")
    @Size(message = "The first name size is not correct", min = 2)
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Size(message = "The last name size is not correct", min = 2)
    private String lastName;
    @NotNull(message = "Password cannot be null")
    @Size(message = "Password size is not correct", min = 8, max = 16)
    private String password;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email is not correct")
    private String email;
}
