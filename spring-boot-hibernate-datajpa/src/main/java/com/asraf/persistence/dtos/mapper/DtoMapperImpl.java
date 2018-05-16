package com.asraf.persistence.dtos.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.asraf.core.dtos.mapper.DtoMapper;
import com.asraf.core.dtos.request.BaseRequestDto;
import com.asraf.core.dtos.response.BaseResponseDto;
import com.asraf.core.entities.BaseEntity;

public abstract class DtoMapperImpl<TEntity extends BaseEntity, TRequestDto extends BaseRequestDto, TResponseDto extends BaseResponseDto>
		implements DtoMapper<TEntity, TRequestDto, TResponseDto> {

	@Autowired
	private ModelMapper modelMapper;

	private Class<TEntity> tEntityType;
	private Class<TResponseDto> tResponseDtoType;

	protected DtoMapperImpl(Class<TEntity> entityType, Class<TResponseDto> responseDtoType) {
		this.tEntityType = entityType;
		this.tResponseDtoType = responseDtoType;
	}

	public TEntity getEntity(TRequestDto requestDto) {
		return modelMapper.map(requestDto, tEntityType);
	}

	public void loadEntity(TRequestDto requestDto, TEntity entity) {
		modelMapper.map(requestDto, entity);
	}

	public TResponseDto getResponseDto(TEntity entity) {
		return modelMapper.map(entity, tResponseDtoType);
	}

	public List<TResponseDto> getResponseDtos(Iterable<TEntity> entities) {
		return StreamSupport.stream(entities.spliterator(), false).map(entity -> getResponseDto(entity))
				.collect(Collectors.toList());
	}

	public List<TResponseDto> getResponseDtos(Collection<TEntity> entities) {
		return entities.stream().map(entity -> getResponseDto(entity)).collect(Collectors.toList());
	}

}
