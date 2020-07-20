package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.AbstractService;
import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarMapper;
import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientService;
import com.swiderski.carrental.crud.exception.CarRentedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentServiceImpl extends AbstractService<Rental, RentalDto> implements RentService {

    private final RentalRepository rentalRepository;
    private final ClientService clientService;
    private final CarMapper carMapper;
    private final RentalMapper rentalMapper;

    public RentServiceImpl(RentalMapper rentalMapper, RentalRepository rentalRepository, ClientService clientService, CarMapper carMapper) {
        super(rentalMapper, rentalRepository);
        this.rentalRepository = rentalRepository;
        this.clientService = clientService;
        this.carMapper = carMapper;
        this.rentalMapper = rentalMapper;
    }

    @Override
    @Transactional
    public RentalDto rentCar(long carId, long clientId) {
        ClientDto clientDto = clientService.getById(clientId);
        Car car = getCarToRent(carId);
        CarDto carDto = carMapper.toDto(car);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setCar(carDto);
        rentalDto.setClient(clientDto);

        return save(rentalDto);
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
