package com.restApi.firstRestAPI.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restApi.firstRestAPI.shared.Utils;
import com.restApi.firstRestAPI.ui.model.request.UserDetailsRequestModel;
import com.restApi.firstRestAPI.ui.model.response.userRest;
import com.restApi.firstRestAPI.usersservice.UserService;

@Service
public class UserServiceImpl implements UserService {

	Map<String,userRest> users;
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}
	
	@Override
	public userRest createUser(UserDetailsRequestModel userDetails) {
		userRest returnValue = new userRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);
		
		if(users == null) {
			users = new HashMap<>();
			users.put(userId, returnValue);
		}
		
		return returnValue;
	}

}
