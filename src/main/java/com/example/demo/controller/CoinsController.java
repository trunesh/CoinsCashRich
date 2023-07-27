package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CoinService;
import com.example.demo.service.RequestService;
import com.example.demo.service.UserService;

@RequestMapping("/coins")
@RestController
public class CoinsController {

	
	@Autowired
	CoinService coinService;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	RequestService requestService;
	
	@GetMapping
	public ResponseEntity<String> getCoinDetails(@RequestHeader(name="X-CMC_PRO_API_KEY")String apiKey,@RequestParam(name="userId") Long userId) {
		
		 if (!userService.isValidUserExist(userId)) {
	            return new ResponseEntity<>("Invalid user", HttpStatus.BAD_REQUEST);
	        }
		String coinDetails = coinService.getCoinDetails(apiKey);
		
		
		requestService.saveRequestDetails(coinDetails,userId);
		
		return new ResponseEntity<>(coinDetails, HttpStatus.OK);
	}
}
