package com.asraf.dtos.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import com.asraf.dtos.request.BaseRequestDto;
import com.asraf.dtos.response.BaseResponseDto;
import com.asraf.entities.BaseEntity;

public interface DtoMapper<TEntity extends BaseEntity, TRequestDto extends BaseRequestDto, TResponseDto extends BaseResponseDto> {

	TEntity getEntity(TRequestDto requestDto);

	void loadEntity(TRequestDto requestDto, TEntity entity);

	TResponseDto getResponseDto(TEntity entity);

	List<TResponseDto> getResponseDtos(Iterable<TEntity> entities);

	List<TResponseDto> getResponseDtos(Collection<TEntity> entities);

	Page<TResponseDto> getResponseDtos(Page<TEntity> pageEntity);
	
}
