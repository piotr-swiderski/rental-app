package com.swiderski.carrental.crud.abstraction;


import com.swiderski.carrental.crud.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto> implements CommonService<D> {

    protected final CommonMapper<E, D> commonMapper;
    protected final CommonRepository<E> commonRepository;

    public AbstractService(CommonMapper<E, D> commonMapper, CommonRepository<E> commonRepository) {
        this.commonMapper = commonMapper;
        this.commonRepository = commonRepository;
    }

    @Override
    @Transactional
    public D save(D dto) {
        E entity = commonMapper.fromDto(dto);
        E savedResult = commonRepository.save(entity);
        return commonMapper.toDto(savedResult);
    }

    @Override
    @Transactional
    public D getById(long id) {
        E entity = getEntityById(id);
        return commonMapper.toDto(entity);
    }

    private E getEntityById(long id) {
        return commonRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "entity"));
    }

    @Override
    @Transactional
    public D update(long id, D dto) {
        dto.setId(id);
        E entity = commonMapper.fromDto(dto);
        commonRepository.save(entity);
        return dto;
    }

    @Override
    @Transactional
    public D delete(long id) {
        E entity = getEntityById(id);
        commonRepository.delete(entity);
        return commonMapper.toDto(entity);
    }
}
