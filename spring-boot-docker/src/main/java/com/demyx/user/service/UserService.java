package com.demyx.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.demyx.user.exceptions.UserReferenceException;
import com.demyx.user.model.UserJpa;
import com.demyx.user.model.dto.UserDto;
import com.demyx.user.repository.UserJpaRepository;
import com.github.javafaker.Faker;

@Service
public class UserService {
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	Faker faker = new Faker();

	public UserDto saveUser(UserDto userDto) {
		UserJpa userjpa=new UserJpa(userDto);
		if(emailExists(userDto.getEmail())) {
			throw new UserReferenceException("This mail is already used");
		}
		
		userjpa.setPassword(passwordEncoder.encode(userDto.getPassword()));

		return new UserDto(userJpaRepository.save(userjpa));
	}
	
	public void fillInitialUsers(){
		
		if(userJpaRepository.findAll().isEmpty()) {
			List<UserJpa> userList= new ArrayList<UserJpa>();
			UserJpa userJpa=new UserJpa();
			userJpa.setUsername("admin");
			userJpa.setEmail("admin1@hotmail.com");
			userJpa.setAge(33);
			userJpa.setPassword(passwordEncoder.encode("admin"));
			userJpa.setName("admin");
			userJpa.setLastname("admin");
			userList.add(userJpa);
			
			String username, name, lastname, email;

			for(int i=0; i<50; i++) {
				userJpa = new UserJpa();
				username= faker.name().username();
				name = faker.name().firstName();
				lastname = faker.name().lastName();
				userJpa.setName( name);
				userJpa.setLastname(lastname);
				userJpa.setUsername(username);
				email = name+"."+lastname+"@drianndemyx.com";
				userJpa.setEmail(email);
				userJpa.setAge(33);
				userJpa.setPassword(passwordEncoder.encode(username));
				userList.add(userJpa);
			
			}

			userJpaRepository.saveAll(userList);
		}
		
		

	}
	
	public boolean emailExists(String email) {
		return userJpaRepository.existsByEmail(email);
		
	}
	
	
	
	public UserDto saveUser(UserDto userDto, UUID id) {
		
		UserJpa userjpa=new UserJpa(userDto);
		Optional<UserJpa> opUser= userJpaRepository.findById(id);
		if(opUser.isPresent()) {
			userjpa=opUser.get();
			userjpa.setAge(userDto.getAge());
			userjpa.setEmail(userDto.getEmail());
			userjpa.setUsername(userDto.getUsername());
			
		}
		return new UserDto(userJpaRepository.save(userjpa));
	}

	public Optional<UserDto> getUser(UUID id) {
		
		Optional<UserJpa> opUserJpa= userJpaRepository.findByUserID(id);
		if(opUserJpa.isPresent()) {
			return Optional.ofNullable(new UserDto(opUserJpa.get()));
		}
		return Optional.ofNullable(null);
	}

	public Page<UserDto> getUsers(Pageable paging, String name) {
		//List<UserDto> userList;
//		if(name==null){
//			userList= userJpaRepository.findAll(paging).stream().map(userJpa->{
//				return new UserDto(userJpa);
//			}).collect(Collectors.toList());
//		}
//		else {
//			userList= userJpaRepository.findAllByUsername(name).stream().map(userJpa->{
//				return new UserDto(userJpa);
//			}).collect(Collectors.toList());
//		}

		List<UserDto> userList =userJpaRepository.findAll(new Specification<UserJpa>() {

			@Override
			public Predicate toPredicate(Root<UserJpa> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate p = cb.conjunction();
				if(!StringUtils.isEmpty(name)) {
					p =  cb.and(p, cb.like(root.get("username"), "%"+name+"%"));
				}
				cq.orderBy(cb.desc(root.get("username")));
				
				return p;
			}
			
		},paging).getContent()
		.stream()
		.map(userJpa->{
			return new UserDto(userJpa);
		}).collect(Collectors.toList());
			
		
	    return new PageImpl<UserDto>(userList, paging, paging.getPageSize());
	

	}

	


	
}
