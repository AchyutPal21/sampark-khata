package com.samparkkhata.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRegisterForm {
    // The annotations are for form validation

    @NotBlank(message = "First name is required")
    @Size(min = 3, message = "Minimum 3 characters")
    private String userFirstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, message = "Minimum 3 characters")
    private String userLastName;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String userEmail;

    @Size(min = 8, message = "Minimum 8 characters are required")
    private String userPassword;

    @Size(min = 8, message = "Minimum 8 characters are required")
    private String userConfirmPassword;

    @NotBlank(message = "Phone number is required")
    private String userPhoneNumber;

    @NotBlank(message = "About name is required")
    @Size(min = 3, message = "Minimum 10 characters")
    private String userAbout;
}
