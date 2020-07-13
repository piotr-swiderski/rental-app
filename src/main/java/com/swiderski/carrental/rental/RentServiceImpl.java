package com.swiderski.carrental.rental;

import com.swiderski.carrental.abstraction.AbstractService;
import com.swiderski.carrental.car.Car;
import com.swiderski.carrental.car.CarDto;
import com.swiderski.carrental.car.CarMapper;
import com.swiderski.carrental.client.ClientDto;
import com.swiderski.carrental.client.ClientService;
import com.swiderski.carrental.exception.CarRentedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public RentalDto rentCar(long carId, long clientId) {
        ClientDto clientDto = clientService.getById(clientId);
        Car car = getCarToRent(carId);
        CarDto carDto = carMapper.toDto(car);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setCar(carDto);
        rentalDto.setClient(clientDto);

        save(rentalDto);
        return rentalDto;
    }

    private Car getCarToRent(long carId) {
        return rentalRepository.getCarToRent(carId).orElseThrow(() -> new CarRentedException(carId));
    }

    @Override
    public RentalDto returnCar(long id, LocalDate returnDate) {
        RentalDto rental = getById(id);
        rental.setRentalEnd(returnDate);
        Rental savedRental = rentalRepository.save(rentalMapper.fromDto(rental));
        return rentalMapper.toDto(savedRental);
    }

    @Override
    public List<CarDto> getAvailableCars(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Car> allAvailableCar = rentalRepository.findAllAvailableCar(paging).getContent();
        return carMapper.toListDto(allAvailableCar);
    }

    @Override
    public List<CarDto> getRentedCars(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Car> allRentedCars = rentalRepository.findAllRentedCars(paging).getContent();
        return carMapper.toListDto(allRentedCars);
    }
}
