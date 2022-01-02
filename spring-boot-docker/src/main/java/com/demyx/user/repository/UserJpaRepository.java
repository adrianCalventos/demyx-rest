package com.demyx.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.demyx.user.model.UserJpa;

@Repository
public interface UserJpaRepository  extends JpaRepository<UserJpa, UUID>, JpaSpecificationExecutor<UserJpa> {
	
	Optional<UserJpa> findByUsername(String name);
	
	List<UserJpa> findAllByUsername(String name);

	
	Optional<UserJpa> findByUserID(UUID id);
		
	Page<UserJpa> findAll(Pageable pageable);
	
	boolean existsByEmail(String mail);

}
