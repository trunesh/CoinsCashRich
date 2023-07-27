package com.example.demo.request;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Validated
public class UserRequest {
	
		@NotEmpty
		@NotBlank
	    private String firstName;

		@NotEmpty
		@NotBlank
	    private String lastName;

	    @Email
	    private String email;

	    @NotEmpty
	    @NotBlank
	    private String mobile;

	    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters long.")
	    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain alphanumeric characters.")
	    @NotBlank
	    private String username;

	    @NotEmpty
	    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters long.")
	    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character.")
	    private String password;
}
