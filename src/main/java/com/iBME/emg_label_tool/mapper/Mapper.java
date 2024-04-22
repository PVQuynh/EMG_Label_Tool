package com.iBME.emg_label_tool.mapper;

import java.util.List;

public interface Mapper<E,D> {
    E toEntity(D dto);
    D toDTO (E entity);

    List<D> toDTOList(List<E> entityList);
    List<E> toEntityList(List<D> dtoList);


}
