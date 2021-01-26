package com.mathieuaime.roadmap.web.mapper;

public interface DtoMapper<D, M> {
  M toModel(D dto);

  D toDto(M model);
}
