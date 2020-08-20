package com.swiderski.carrental.crud.abstraction;


import com.swiderski.carrental.crud.exception.NotFoundException;
import com.swiderski.carrental.pdfGenerator.PdfGenerator;
import com.swiderski.carrental.pdfGenerator.PdfGeneratorSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto, V extends CommonParam> implements CommonService<D, V> {

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

    @Override
    public byte[] getPdfReport(V param) {
        Page<D> all = getAll(param, PageRequest.of(0, 50, Sort.by("id")));
        return PdfGenerator.build(all.getContent());
    }
}
