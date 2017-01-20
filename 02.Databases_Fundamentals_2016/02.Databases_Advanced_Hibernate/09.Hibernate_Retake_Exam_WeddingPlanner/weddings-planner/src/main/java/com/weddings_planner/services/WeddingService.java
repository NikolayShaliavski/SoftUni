package com.weddings_planner.services;

import java.text.ParseException;

import com.weddings_planner.dtos.WeddingImportDto;

public interface WeddingService {
	
	String save(WeddingImportDto weddingDto) throws ParseException;
}