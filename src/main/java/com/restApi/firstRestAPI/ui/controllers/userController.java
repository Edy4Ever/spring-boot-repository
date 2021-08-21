package com.restApi.firstRestAPI.ui.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restApi.firstRestAPI.exceptions.UserServiceException;
import com.restApi.firstRestAPI.ui.model.request.UpdateUserDetailsRequestModel;
import com.restApi.firstRestAPI.ui.model.request.UserDetailsRequestModel;
import com.restApi.firstRestAPI.ui.model.response.userRest;
import com.restApi.firstRestAPI.usersservice.UserService;

@RestController
@RequestMapping("/users")
public class userController {
	
	Map<String,userRest> users;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="10") int page,
							@RequestParam(value="limit", defaultValue="50") int limit,
							@RequestParam(value="sort", defaultValue="desc", required=false) String sort){
		return "get Users was called with page: "+page+" and limit: "+ limit + " and sort :"+sort;
	}
	
	@SuppressWarnings("unused")
	@GetMapping(path="/{userId}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	//ResponseEntity to send a different HTTP response instead of 200 ok for instance.
	//public userRest getUser(@PathVariable String userId){
	public ResponseEntity<userRest> getUser(@PathVariable String userId){
		
		if(true) throw new UserServiceException("A user service exception is thrown");
		
		if(users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK); //or HttpStatus.badrequest and so on
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); //or HttpStatus.badrequest and so on
		}
		//return returnValue;
	}
	
	//to let know our application it can consume either JSON or XML format
	//in POSTMAN we have to specify in header "CONTENT-TYPE application/json or application/xml"
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
				produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	//public String createUser(@RequestBody UserDetailsRequestModel userDetails){
	public ResponseEntity<userRest>  createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){
		
		userRest returnValue = userService.createUser(userDetails);
		//return returnValue;
		return new ResponseEntity<userRest>(returnValue, HttpStatus.OK); 
	}
	
	@PutMapping(path="/{userId}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public userRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails){
		userRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		users.remove(id);
		return ResponseEntity.noContent().build();
	}
}
