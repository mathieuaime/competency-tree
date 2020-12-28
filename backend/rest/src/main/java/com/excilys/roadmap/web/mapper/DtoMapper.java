package com.excilys.roadmap.web.mapper;

public interface DtoMapper<D, M> {
  M toModel(D dto);

  D toDto(M model);
}
