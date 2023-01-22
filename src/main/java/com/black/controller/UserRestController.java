package com.black.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.black.serviceImpl.UserServiceImpl;
import com.black.utils.ApiResponse;
import com.black.utils.UserDto;


	@RestController
	//@RequestMapping("/api/users/")
	public class UserRestController {
		@Autowired
		private UserServiceImpl service;

		@PostMapping("/create")
		public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
			UserDto createUser = service.createUser(userDto);
			return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
		}

		@PutMapping("/update/{userId}")
		public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
				@PathVariable("userId") Integer uId) {
			UserDto updateUser = service.updateUser(userDto, uId);
			return ResponseEntity.ok(updateUser);
		}

		@DeleteMapping("/delete/{userId}")
		public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id) {
			service.deleteUser(id);
			return new ResponseEntity<>(new ApiResponse("User Record Deleted Of User Id:"+id), HttpStatus.OK);
		}

		@GetMapping("/getAll")
		public ResponseEntity<List<UserDto>> getAllUser() {
			List<UserDto> allUser = service.getAllUser();
			return ResponseEntity.ok(allUser);
		}

		@GetMapping("getById/{userId}")
		public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
			UserDto user = service.getUser(userId);
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		}
}
