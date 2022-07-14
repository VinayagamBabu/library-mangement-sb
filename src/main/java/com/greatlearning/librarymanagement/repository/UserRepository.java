package com.greatlearning.librarymanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.librarymanagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);
}
