package com.black.utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private Integer userId;
	@NotEmpty
	@Size(min = 3, message = "Username must be minimum of 3 characters")
	private String name;
	@Email(message = "Given email address not valid please enter valid Email")
	private String email;
	@Size(min = 4, max = 10, message = " Password must be min of 4 and max of 10")
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;

}
