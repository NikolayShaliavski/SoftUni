package com.weddings_planner.io.parsers.modelParsers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ModelParser {

	private ModelMapper modelMapper;

	public ModelParser() {
	}

	public <S, D> D convert(S source, Class<D> destination) {
		this.modelMapper = new ModelMapper();
		D converted = this.modelMapper.map(source, destination);
		return converted;
	}

	public <S, D> D convert(S source, Class<D> destination, PropertyMap<S, D> propertyMap) {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addMappings(propertyMap);
		D converted = this.modelMapper.map(source, destination);
		return converted;
	}

	public <S, D> List<D> convertToEntities(S[] sourceDtos, Class<D> destination) {
		this.modelMapper = new ModelMapper();
		List<D> entities = new ArrayList<>();
		for (S source : sourceDtos) {
			D entity = this.modelMapper.map(source, destination);
			entities.add(entity);
		}
		return entities;
	}

	public <S, D> List<D> convertToEntities(S[] sourceDtos, Class<D> destination, PropertyMap<S, D> propertyMap) {
		this.modelMapper = new ModelMapper();
		List<D> entities = new ArrayList<>();
		this.modelMapper.addMappings(propertyMap);
		for (S source : sourceDtos) {
			D entity = this.modelMapper.map(source, destination);
			entities.add(entity);
		}
		return entities;
	}

	public <S, D> List<D> convertToDtos(List<S> source, Class<D> destination) {
		this.modelMapper = new ModelMapper();
		List<D> dtos = new ArrayList<>();
		for (S s : source) {
			D dto = this.modelMapper.map(s, destination);
			dtos.add(dto);
		}
		return dtos;
	}

	public <S, D> List<D> convertToDtos(List<S> source, Class<D> destination, PropertyMap<S, D> propertyMap) {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addMappings(propertyMap);
		List<D> dtos = new ArrayList<>();
		for (S s : source) {
			D dto = this.modelMapper.map(s, destination);
			dtos.add(dto);
		}
		return dtos;
	}
}
