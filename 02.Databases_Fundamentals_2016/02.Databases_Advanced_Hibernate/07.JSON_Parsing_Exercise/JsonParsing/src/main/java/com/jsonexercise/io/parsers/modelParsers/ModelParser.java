package com.jsonexercise.io.parsers.modelParsers;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelParser {

    private ModelMapper modelMapper;

    public ModelParser() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D convertToEntity(S source, Class<D> destination) {
        D entity = this.modelMapper.map(source, destination);
        return entity;
    }

    public <S, D> List<D> convertToEntity(S[] sourceDtos, Class<D> destination) {
        List<D> entities = new ArrayList<>();
        for (S source : sourceDtos) {
            D entity = this.modelMapper.map(source, destination);
            entities.add(entity);
        }
        return entities;
    }

    public <S, D> D convertToDto(S source, Class<D> destination) {
        D dto = this.modelMapper.map(source, destination);
        return dto;
    }

    public <S, D> D convertToDto(S source, Class<D> destination, PropertyMap<S, D> propertyMap) {
        this.modelMapper.addMappings(propertyMap);
        D dto = this.modelMapper.map(source, destination);
        return dto;
    }

    public <S, D> List<D> convertToDto(List<S> source, Class<D> destination) {
        List<D> dtos = new ArrayList<>();
        for (S s : source) {
            D dto = this.modelMapper.map(s, destination);
            dtos.add(dto);
        }
        return dtos;
    }

    public <S, D> List<D> convertToDto(List<S> source,
                                       Class<D> destination,
                                       PropertyMap<S, D> propertyMap) {
        this.modelMapper.addMappings(propertyMap);
        List<D> dtos = new ArrayList<>();
        for (S s : source) {
            D dto = this.modelMapper.map(s, destination);
            dtos.add(dto);
        }
        return dtos;
    }
}
