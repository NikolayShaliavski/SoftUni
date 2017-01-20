package com.weddings_planner.services;

import java.text.ParseException;

import com.weddings_planner.dtos.PersonImportDto;

public interface PersonService {
	
	String save(PersonImportDto personDto) throws ParseException;
}