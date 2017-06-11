package com.softuni.store.utils.parser;

import com.softuni.store.utils.parser.interfaces.ModelParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import javax.ejb.Stateless;

@Stateless
public class ModelParserImpl implements ModelParser {

    private ModelMapper modelMapper;

    public ModelParserImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        D convertedObject = null;
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }

    @Override
    public <S, D> D convert(S source, Class<S> sourceClass, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        D convertedObject = null;
        TypeMap<S, D> typeMap = this.modelMapper.getTypeMap(sourceClass, destinationClass);
        if (typeMap == null) {
            this.modelMapper.addMappings(propertyMap);
        }
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }
}
