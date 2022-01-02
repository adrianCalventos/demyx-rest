package com.demyx.user.model;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.demyx.entity.AuditableJpa;
import com.demyx.user.model.dto.UserDto;


@Entity
@Table(name = "User", schema="db")
public class UserJpa extends AuditableJpa<String>{
	
	private String password;
	
	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID userID;
	
	@Column(name = "username")
	private String username;
	
    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    private String name;
    
    @Column(name = "lastname")
    @NotNull(message = "Name cannot be null")
    private String lastname;
    
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    @Column(name = "age")
    private int age;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;
    
    public UserJpa() {};

    
    public UserJpa(UserDto userDto) {
    	this.age=userDto.getAge();
    	this.email=userDto.getEmail();
    	this.username=userDto.getUsername();
    	this.name=userDto.getName();
    	this.lastname=userDto.getLastname();
    }
    
    
	public String getUsername() {
		// TODO Auto-generated method stub
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


	public UUID getUserID() {
		return userID;
	}


	public void setUserID(UUID userID) {
		this.userID = userID;
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
