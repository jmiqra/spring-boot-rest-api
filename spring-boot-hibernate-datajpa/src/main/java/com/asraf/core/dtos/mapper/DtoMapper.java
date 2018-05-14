package com.asraf.core.dtos.mapper;

import java.util.List;

import com.asraf.core.dtos.request.BaseRequestDto;
import com.asraf.core.dtos.response.BaseResponseDto;
import com.asraf.core.entities.BaseEntity;

public interface DtoMapper<TEntity extends BaseEntity, TRequestDto extends BaseRequestDto, TResponseDto extends BaseResponseDto> {
	TEntity getEntity(TRequestDto requestDto);

	void loadEntity(TRequestDto requestDto, TEntity entity);

	TResponseDto getResponseDto(TEntity entity);

	List<TResponseDto> getResponseDtos(Iterable<TEntity> entities);
}
