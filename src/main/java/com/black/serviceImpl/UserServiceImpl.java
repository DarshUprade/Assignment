package com.black.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black.entity.User;
import com.black.exception.ResourceNotFoundException;
import com.black.repo.UserRepo;
import com.black.service.UserService;
import com.black.utils.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo repo;

	@Override
	public UserDto createUser(UserDto user) {
		User save = repo.save(this.dtoToUser(user));
		UserDto userToDto = this.userToDto(save);
		return userToDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "Id Not Found", userId));
		if (userDto.getName() != null) {
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());
			User saved = repo.save(user);
			UserDto userToDto = this.userToDto(saved);
			return userToDto;
		}
		return userDto;
	}

	@Override
	public UserDto getUser(Integer userId) {
		User byId = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
		return this.userToDto(byId);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = repo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		repo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

}
