package com.demyx.user.controllers;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demyx.user.exceptions.UserReferenceException;
import com.demyx.user.model.dto.UserDto;
import com.demyx.user.service.UserService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})@ResponseStatus(value = HttpStatus.NOT_FOUND)
@RequestMapping("/user")
public class UserController implements ErrorController{
	
	@Autowired
	public UserService userService;
	
	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	
	@PostMapping(path = {""})
	public UserDto createUser(@RequestBody UserDto userDto) {
		try {
			return userService.saveUser(userDto);
		}catch(Exception e) {
			throw new UserReferenceException("Invalid transaction reference provided :"+e);

		}
		
	}
	
	
	@PutMapping("/{id}")
	public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable("id") UUID id) {
		try {
			return userService.saveUser(userDto, id);
		}catch(Exception e) {
			throw new UserReferenceException("Invalid transaction reference provided :"+e);

		}
		
	}
	
	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable ("id") UUID id) {
		Optional<UserDto> opUserDto = userService.getUser(id);
		if(opUserDto.isPresent()) {
			return opUserDto.get();
		}
		
		throw new UserReferenceException("Invalid transaction reference provided");
	}
	
	/**
	 * 
	 * @param name search concept to find users
	 * @param page num of page init by 0
	 * @param size num of elements by page
	 * @return Page of users
	 */
	@GetMapping("")
	public Page<UserDto> getUsers( @RequestParam(required=false) String name,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable paging = PageRequest.of(page, size);
		return userService.getUsers(paging, name);
	}
	
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
       

}
