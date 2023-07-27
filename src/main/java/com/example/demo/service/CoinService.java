package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service	
public class CoinService {
	
	@Autowired(required=true)
	RestTemplate restTemplate;
	
	
	public String getCoinDetails(final String apiKey) {
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);

        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API URL
        String apiUrl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=BTC,ETH,LTC";

        try {
        // Make the GET request with RestTemplate
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody(); // Return the API response data
        } else {
            // Handle error responses here
            return null;
        }
        
        }catch(Exception e) {
        	return e.getMessage();
        }
        // Check if the request was successful (HTTP status code 200)
       
	}
}
