package com.example.myscheduler.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {

	private Integer id;
	private String name;
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String loginPass;
	
}
