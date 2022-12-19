package com.sportify.UserMicroService.service;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sportify.UserMicroService.entity.User;
import com.sportify.UserMicroService.entity.UserRole;
import com.sportify.UserMicroService.repository.UserRepository;
import com.sportify.UserMicroService.repository.UserRoleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private UserRoleRepository userRoleRepository;

	  
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }


	    public User getUserById(int id) {
	        return userRepository.getOne(id);
	    }


	    public User getUserByName(String userName) {
	        return userRepository.findByUserName(userName);
	    }


	    public User saveUser(User user) {
	        
	        UserRole role = userRoleRepository.findUserRoleByRoleName("Joueur");
	        user.setRole(role);
	        return userRepository.save(user);
	    }

}
