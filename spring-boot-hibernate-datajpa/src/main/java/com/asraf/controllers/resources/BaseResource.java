package com.asraf.controllers.resources;

import org.springframework.hateoas.ResourceSupport;

import com.asraf.dtos.mapper.ResponseDtoMapper;
import com.asraf.dtos.response.BaseEntityResponseDto;
import com.asraf.entities.BaseEntity;

import lombok.Getter;

@Getter
public abstract class BaseResource<TEntity extends BaseEntity, TResponseDto extends BaseEntityResponseDto>
		extends ResourceSupport {

	private final TResponseDto content;

	public BaseResource(final TEntity entity, final ResponseDtoMapper<TEntity, TResponseDto> responseDtoMapper) {
		this.content = responseDtoMapper.getResponseDto(entity);
	}

}
