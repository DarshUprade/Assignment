package com.black.utils;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Integer postId;
	@NotEmpty(message = "Please write Something")
	private String title;
	@NotEmpty(message = "Please write Something")
	@Size(min = 5, max = 2000, message = "Please Write Something")
	private String content;

	private Date addedDate;

	private UserDto user;
}
