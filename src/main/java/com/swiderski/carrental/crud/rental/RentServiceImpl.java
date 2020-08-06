package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.AbstractService;
import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarMapper;
import com.swiderski.carrental.crud.car.CarService;
import com.swiderski.carrental.crud.client.ClientService;
import com.swiderski.carrental.crud.exception.CarRentedException;
import com.swiderski.carrental.crud.specification.SearchCriteria;
import com.swiderski.carrental.crud.specification.SpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.swiderski.carrental.crud.specification.SearchOperation.GREATER_THAN_EQUAL;
import static com.swiderski.carrental.crud.specification.SearchOperation.LESS_THAN_EQUAL;

@Service
public class RentServiceImpl extends AbstractService<Rental, RentalDto> implements RentService {

    private final RentalRepository rentalRepository;
    private final ClientService clientService;
    private final CarMapper carMapper;
    private final RentalMapper rentalMapper;
    private CarService carService;

    public RentServiceImpl(RentalMapper rentalMapper, RentalRepository rentalRepository, ClientService clientService, CarMapper carMapper, CarService carService) {
        super(rentalMapper, rentalRepository);
        this.rentalRepository = rentalRepository;
        this.clientService = clientService;
        this.carMapper = carMapper;
        this.rentalMapper = rentalMapper;
        this.carService = carService;
    }

    @Override
    public Page<RentalDto> getAll(RentalParam rentalParam, Pageable pageable) {
        SpecificationBuilder<Rental> specificationBuilder = new SpecificationBuilder<>();
        specificationBuilder
                .add(new SearchCriteria(Rental_.RENTAL_BEGIN, rentalParam.getRentedFrom(), GREATER_THAN_EQUAL))
                .add(new SearchCriteria(Rental_.RENTAL_END, rentalParam.getRentedTo(), LESS_THAN_EQUAL));

//        Specification<Rental> specification = new Specification<>() {
//            @Override
//            public Predicate toPredicate(Root<Rental> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
//                return cb.like(root.join(Rental_.CAR).get(Car_.BRAND), "%" + rentalParam.getHasBrand() + "%");
//            }
//        };

        Page<Rental> rentalPage = commonRepository.findAll(specificationBuilder, pageable);
        List<RentalDto> rentalDtos = commonMapper.toListDto(rentalPage.getContent());
        return new PageImpl<>(rentalDtos, pageable, rentalPage.getTotalElements());
    }

    @Override
    @Transactional
    public RentalDto rentCar(long carId, long clientId) {
        Rental rental = new Rental();
        rental.setClient(clientService.getEntity(clientId));
        rental.setCar(carService.getEntity(carId));

        Rental saved = commonRepository.save(rental);
        return commonMapper.toDto(saved);
    }

    private Car getCarToRent(long carId) {
        return rentalRepository.getCarToRent(carId).orElseThrow(() -> new CarRentedException(carId));
    }

    @Override
    @Transactional
    public RentalDto returnCar(long id, LocalDate returnDate) {
        RentalDto rental = getById(id);
        rental.setRentalEnd(returnDate);
        Rental savedRental = rentalRepository.save(rentalMapper.fromDto(rental));
        return rentalMapper.toDto(savedRental);
    }

    @Override
    @Transactional
    public Page<CarDto> getAvailableCars(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Car> allAvailableCarPage = rentalRepository.findAllAvailableCar(paging);
        List<CarDto> pageResult = carMapper.toListDto(allAvailableCarPage.getContent());
        return new PageImpl<>(pageResult, paging, allAvailableCarPage.getTotalElements());
    }

    @Override
    @Transactional
    public Page<CarDto> getRentedCars(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Car> allRentedCars = rentalRepository.findAllRentedCars(paging);
        List<CarDto> pageList = carMapper.toListDto(allRentedCars.getContent());
        return new PageImpl<>(pageList, paging, allRentedCars.getTotalElements());
    }
}
