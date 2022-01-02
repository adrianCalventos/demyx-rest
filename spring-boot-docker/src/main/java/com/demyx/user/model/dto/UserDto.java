package com.demyx.user.model.dto;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.demyx.user.model.UserJpa;

public class UserDto {
	

	private String password;
	
    @NotNull(message = "Username cannot be null")
	private String username;
	
    @NotNull(message = "Name cannot be null")
    private String name;
    
    @NotNull(message = "Lastname cannot be null")
    private String lastname;
    
    private UUID userId;
    
    
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @Email(message = "Email should be valid")
    private String email;
    
   public UserDto() {};
    public UserDto(UserJpa userJpa) {
    	this.password=userJpa.getPassword();
    	this.age=userJpa.getAge();
    	this.email=userJpa.getEmail();
    	this.username=userJpa.getUsername();
    	this.name=userJpa.getUsername();
    	this.lastname=userJpa.getLastname();
    	this.userId=userJpa.getUserID();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    
    
    
    
	

}
