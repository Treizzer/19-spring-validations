package com.treizer.spring_validations.service.interfaces;

import java.util.List;

public interface ICommonService<T, TI, TU> {

    List<T> findAll();

    T findById(Long id);

    T save(TI insertDto);

    T update(TU updateDto, Long id);

    T deleteById(Long id);

}
