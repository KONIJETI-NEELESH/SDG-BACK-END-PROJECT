package com.sdg.goals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdg.goals.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	

	 User findByUsernameAndPassword(String username, String password);
}
