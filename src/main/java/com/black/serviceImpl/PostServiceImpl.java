package com.black.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black.entity.Post;
import com.black.entity.User;
import com.black.exception.ResourceNotFoundException;
import com.black.repo.PostRepo;
import com.black.repo.UserRepo;
import com.black.service.PostService;
import com.black.utils.PostDto;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto dto, Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "User Id", userId));

		Post post = mapper.map(dto, Post.class);
		post.setAddedDate(new Date());

		post.setUser(user);

		Post saved = postRepo.save(post);
		return mapper.map(saved, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setTitle(postDto.getTitle());
		post.setAddedDate(new Date());
		post.setContent(postDto.getContent());
		Post save = postRepo.save(post);
		return mapper.map(save, PostDto.class);
	}

	@Override
	public boolean deletePost(Integer postId) {
		boolean flag = false;
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		if (post != null) {
			postRepo.delete(post);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts = postRepo.findAll();
		List<PostDto> collect = posts.stream().map((obj) -> mapper.map(obj, PostDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		PostDto map = mapper.map(post, PostDto.class);
		return map;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

}
