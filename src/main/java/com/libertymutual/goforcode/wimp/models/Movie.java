package com.libertymutual.goforcode.wimp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//jpa

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	

	private Long budget;
	
	private Date releaseDate;
	
	@Column(length=300, nullable=false)
	private String title;
	
	@Column(length=255, nullable=false)
	private String name;
	
	@Column(length=500, nullable=false)
	private String distributor;
		
}