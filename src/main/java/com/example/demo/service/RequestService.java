package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiRequestEntity;
import com.example.demo.repository.ApiRequestRepository;

@Service	
public class RequestService {
	
	@Autowired
	ApiRequestRepository requestRepository;
	
	
	public void saveRequestDetails(String coinResponse,Long userId) {
		
		ApiRequestEntity apiRequestEntity=new ApiRequestEntity();
		
		apiRequestEntity.setRequestResponse(coinResponse);
		apiRequestEntity.setUserId(userId);
		apiRequestEntity.setTimestamp(LocalDateTime.now());
		
		requestRepository.save(apiRequestEntity);
		
	}
}
