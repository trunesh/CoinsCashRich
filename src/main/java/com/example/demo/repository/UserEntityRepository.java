package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserEntityRepository extends  JpaRepository<UserEntity,Long> {

	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByUsernameAndEmail(String username,String email);

}
