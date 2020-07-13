package com.swiderski.carrental.abstraction;


import com.swiderski.carrental.exception.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;


public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto> implements CommonService<D> {

    protected final CommonMapper<E, D> commonMapper;
    protected final CommonRepository<E> commonRepository;

    public AbstractService(CommonMapper<E, D> commonMapper, CommonRepository<E> commonRepository) {
        this.commonMapper = commonMapper;
        this.commonRepository = commonRepository;
    }

    @Override
    public D save(D dto) {
        E entity = commonMapper.fromDto(dto);
        E savedResult = commonRepository.save(entity);
        return commonMapper.toDto(savedResult);
    }

    @Override
    public D getById(long id) {
        E entity = getEntityById(id);
        return commonMapper.toDto(entity);
    }

    private E getEntityById(long id) {
        return commonRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "entity"));
    }

    @Override
    public List<D> getAll(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<E> pagedResult = commonRepository.findAll(paging).getContent();
        return commonMapper.toListDto(pagedResult);
    }

    @Override
    public D update(long id, D dto) {
        dto.setId(id);
        E entity = commonMapper.fromDto(dto);
        commonRepository.save(entity);
        return dto;
    }

    @Override
    public D delete(long id) {
        E entity = getEntityById(id);
        commonRepository.delete(entity);
        return commonMapper.toDto(entity);
    }
}
