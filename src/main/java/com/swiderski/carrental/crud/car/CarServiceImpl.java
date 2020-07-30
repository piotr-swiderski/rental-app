package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.AbstractService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl extends AbstractService<Car, CarDto> implements CarService {

    public CarServiceImpl(CarMapper carMapper, CarRepository commonRepository) {
        super(carMapper, commonRepository);
    }

    @Override
    public List<CarDto> getAllWithoutSpec(int pageNo, int pageSize, String sortBy) {
        List<Car> content = commonRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy))).getContent();
        return commonMapper.toListDto(content);
    }
}
