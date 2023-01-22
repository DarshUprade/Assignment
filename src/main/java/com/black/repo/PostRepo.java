package com.black.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.black.entity.Post;
import com.black.entity.User;

public interface PostRepo  extends JpaRepository<Post,Integer>{
	public List<Post> findByUser(User user);
	
        
}
