package com.sportify.UserMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.UserMicroService.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserName(String userName);

}
