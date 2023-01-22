package com.black.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.black.serviceImpl.PostServiceImpl;
import com.black.utils.PostDto;


@RestController
//@RequestMapping("/api/")
public class PostRestController {

	@Autowired
	private PostServiceImpl service;

	// create
	@PostMapping("/createPost/userId/{userId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable Integer userId) {
		PostDto createdPost = service.createPost(dto, userId);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	// update Post by id
	@PutMapping("/updatePost/postId/{postId}/")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable Integer postId) {
		PostDto updatePost = service.updatePost(dto, postId);
		return new ResponseEntity<>(updatePost, HttpStatus.CREATED);
	}

	// delete post
	@GetMapping("/delete/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
		boolean deletePost = service.deletePost(postId);
		String msg = "invalid PostId ...";
		if (deletePost == true) {
			msg = "Post Deleted Successfully..";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	// get All Post
	@GetMapping("/allPosts")
	public ResponseEntity<List<PostDto>> getAllPosts(	) {
		List<PostDto> allPost = service.getAllPost();
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}

	// get post by Id
	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto post = service.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	// get by user
	@GetMapping("/getPostByUserId/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> postDto = service.getPostsByUser(userId);
		return new ResponseEntity<>(postDto, HttpStatus.OK);
	}

}
