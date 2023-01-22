package com.black.service;

import java.util.List;

import com.black.utils.UserDto;

public interface UserService {
	
public UserDto createUser(UserDto user);
	
	public UserDto updateUser(UserDto user,Integer userId);
	
	public UserDto getUser(Integer userId);
	
	public List<UserDto> getAllUser();
	 
	public void deleteUser(Integer userId);

}
