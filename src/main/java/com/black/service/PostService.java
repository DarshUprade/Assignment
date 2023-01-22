package com.black.service;

import java.util.List;

import com.black.utils.PostDto;


public interface PostService {
	// create post
	public PostDto createPost(PostDto dto, Integer userId);

	// update Post
	public PostDto updatePost(PostDto postDto, Integer postId);

	// delete post
	public boolean deletePost(Integer postId);

	// get all post
	public List<PostDto> getAllPost();

	// get single post
	public PostDto getPostById(Integer postId);

	// get all post by user
	public List<PostDto> getPostsByUser(Integer userId);

}
