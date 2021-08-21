package com.restApi.firstRestAPI.usersservice;

import com.restApi.firstRestAPI.ui.model.request.UserDetailsRequestModel;
import com.restApi.firstRestAPI.ui.model.response.userRest;

public interface UserService {
	userRest createUser(UserDetailsRequestModel userDetails);
}
