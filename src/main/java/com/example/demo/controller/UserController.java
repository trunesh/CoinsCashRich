package com.example.demo.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.request.UserRequest;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@Validated
@RequestMapping("/coins")
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/health")
    public String checkHealth() {
        return "{\"status\": \"OK\"}";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody  UserRequest userRequest) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        
        String token = jwtTokenUtil.generateToken(userRequest.getUsername());
        JSONObject response = new JSONObject();
   
        try {
        	json = ow.writeValueAsString(userRequest);
            response = new JSONObject(json);
        	userService.registerUser(userRequest.getFirstName(), userRequest.getLastName(),
                    userRequest.getEmail(), userRequest.getMobile(),
                    userRequest.getUsername(), userRequest.getPassword());
        } catch (final DataIntegrityViolationException ex) {
            JSONObject ob = new JSONObject();
            ob.put("message", "Field is already exist");
            ob.put("DetailedMessage", ex.getMessage());
            return new ResponseEntity<>(ob.toString(), HttpStatus.BAD_REQUEST);
        } catch (final Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("token", token);
        return new ResponseEntity<>(response.toString(), HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/signup")
    public ResponseEntity<String> updateUser(@Valid @RequestBody  UserRequest userRequest) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        
        try {
        	json = ow.writeValueAsString(userRequest);
        	userService.updateDetails(userRequest.getFirstName(), userRequest.getLastName(),
                    userRequest.getEmail(), userRequest.getMobile(),
                    userRequest.getUsername(),userRequest.getPassword());
        } catch (final Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
 
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
