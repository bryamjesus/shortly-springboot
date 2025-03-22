package com.bjtg.shortly.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {

    private final ModelMapper modelMapper;

    public UrlMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureModelMapper();
    }

    private void configureModelMapper() {
        //modelMapper.typeMap();

    }

}
