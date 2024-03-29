package com.demyx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableJpa <U> {
	
	 @CreatedDate
	 @Column(name = "created_date")
	 private Date createdDate;
	 
	 @LastModifiedDate
	 @Column(name = "last_modified_date")
	 private Date lastModifiedDate;


}
