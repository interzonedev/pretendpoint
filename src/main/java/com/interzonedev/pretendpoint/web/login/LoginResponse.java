package com.interzonedev.pretendpoint.web.login;

import com.interzonedev.pretendpoint.web.PretendPointResponse;

public class LoginResponse extends PretendPointResponse {

	private String authToken;

	public LoginResponse(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthToken() {
		return authToken;
	}

}
