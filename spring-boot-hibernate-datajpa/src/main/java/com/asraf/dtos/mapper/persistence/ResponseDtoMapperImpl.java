package com.asraf.dtos.mapper.persistence;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.asraf.dtos.mapper.ResponseDtoMapper;
import com.asraf.dtos.response.BaseResponseDto;
import com.asraf.entities.BaseEntity;

public abstract class ResponseDtoMapperImpl<TEntity extends BaseEntity, TResponseDto extends BaseResponseDto>
		extends DtoMapperImpl implements ResponseDtoMapper<TEntity, TResponseDto> {

	private Class<TResponseDto> tResponseDtoType;

	protected ResponseDtoMapperImpl(ModelMapper modelMapper, Class<TResponseDto> responseDtoType) {
		super(modelMapper);
		this.tResponseDtoType = responseDtoType;
	}

	public TResponseDto getResponseDto(TEntity entity) {
		return super.modelMapper.map(entity, tResponseDtoType);
	}

	public List<TResponseDto> getResponseDtos(Iterable<TEntity> entities) {
		return StreamSupport.stream(entities.spliterator(), false).map(entity -> getResponseDto(entity))
				.collect(Collectors.toList());
	}

	public List<TResponseDto> getResponseDtos(Collection<TEntity> entities) {
		return entities.stream().map(entity -> getResponseDto(entity)).collect(Collectors.toList());
	}

	public Page<TResponseDto> getResponseDtos(Page<TEntity> pageEntity) {
		Page<TResponseDto> pagedResponseDto = new PageImpl<TResponseDto>(this.getResponseDtos(pageEntity.getContent()),
				pageEntity.getPageable(), pageEntity.getTotalElements());
		return pagedResponseDto;
	}

	protected ResponseDtoMapperImpl<TEntity, TResponseDto> setEntityToResponsePropertyMap(
			PropertyMap<TEntity, TResponseDto> entityToResponsePropertyMap) {
		if (entityToResponsePropertyMap != null) {
			super.modelMapper.addMappings(entityToResponsePropertyMap);
		}
		return this;
	}

	protected <TSource, TDestination> ResponseDtoMapperImpl<TEntity, TResponseDto> setNestedObjectPropertyMap(
			PropertyMap<TSource, TDestination> nestedObjectPropertyMap) {
		if (nestedObjectPropertyMap != null) {
			super.modelMapper.addMappings(nestedObjectPropertyMap);
		}
		return this;
	}
}
