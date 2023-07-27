package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserEntityRepository;

@Service
public class UserService {

	
	
	private final UserEntityRepository userRepository;
	
	
    private final PasswordEncoder passwordEncoder;

    
    public UserService(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerUser(String firstName, String lastName, String email, String mobile, String username, String password) {
        // Hash the password before storing it in the database
        String hashedPassword = passwordEncoder.encode(password);
        


        UserEntity user = new UserEntity(firstName, lastName, email, mobile, username, hashedPassword);
        return userRepository.save(user);
    }
    
    
    public UserEntity updateDetails(String firstName, String lastName, String email, String mobile, String username,String password) throws UserNotFoundException {

    	Optional<UserEntity> userInfo = userRepository.findByUsernameAndEmail(username,email);
    	if(userInfo.isEmpty()) {
    		throw new UserNotFoundException("user not present");
    	}
    	
    	UserEntity userEntity = userInfo.get();
    	userEntity.setFirstName(firstName);
    	userEntity.setLastName(lastName);
    	userEntity.setMobile(mobile);
    	
    	String hashedPassword = passwordEncoder.encode(password);
    	userEntity.setPassword(hashedPassword);
   
    	
        return userRepository.save(userEntity);
    }
    
    public boolean isValidUser(String username, String password) {
        Optional<UserEntity> ob = userRepository.findByUsername(username);
        if (ob.isPresent()) {
            // Use the PasswordEncoder to check if the provided password matches the stored hashed password
            return passwordEncoder.matches(password, ob.get().getPassword());
        }
        return false;
    }
    
    
    public boolean isValidUserExist(Long userId) {
    	Optional<UserEntity> user = userRepository.findById(userId);
  
    	if(user.isPresent()) {
    		return true;
    	}
    	return false;
    }
}
