package com.sportify.UserMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.UserMicroService.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	   UserRole findUserRoleByRoleName(String roleName);

}
