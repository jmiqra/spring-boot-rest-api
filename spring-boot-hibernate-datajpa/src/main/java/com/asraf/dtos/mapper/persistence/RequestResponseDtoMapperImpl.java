package com.asraf.dtos.mapper.persistence;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.asraf.dtos.mapper.RequestResponseDtoMapper;
import com.asraf.dtos.request.BaseRequestDto;
import com.asraf.dtos.response.BaseEntityResponseDto;
import com.asraf.entities.BaseEntity;

public abstract class RequestResponseDtoMapperImpl<TEntity extends BaseEntity, TResponseDto extends BaseEntityResponseDto, TRequestDto extends BaseRequestDto>
		extends ResponseDtoMapperImpl<TEntity, TResponseDto>
		implements RequestResponseDtoMapper<TEntity, TResponseDto, TRequestDto> {

	private Class<TEntity> tEntityType;

	protected RequestResponseDtoMapperImpl(ModelMapper modelMapper, Class<TResponseDto> responseDtoType,
			Class<TEntity> entityType) {
		super(modelMapper, responseDtoType);
		this.tEntityType = entityType;
	}

	public TEntity getEntity(TRequestDto requestDto) {
		return modelMapper.map(requestDto, tEntityType);
	}

	public void loadEntity(TRequestDto requestDto, TEntity entity) {
		modelMapper.map(requestDto, entity);
	}

	protected RequestResponseDtoMapperImpl<TEntity, TResponseDto, TRequestDto> setRequestToEntityPropertyMap(
			PropertyMap<TRequestDto, TEntity> requestToEntityPropertyMap) {
		if (requestToEntityPropertyMap != null) {
			this.modelMapper.addMappings(requestToEntityPropertyMap);
		}
		return this;
	}

}
