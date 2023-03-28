package com.sdg.goals.dto;

import java.util.List;

public class UserDTO {

    private String username;
    private String password;
    private List<String> authorities;

    public UserDTO() {
    }
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    

    public UserDTO(String username, String password, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

    // getters and setters for username, password, and authorities
}
