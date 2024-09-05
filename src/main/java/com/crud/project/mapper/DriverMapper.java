package com.crud.project.mapper;

import com.crud.project.domain.Driver;
import com.crud.project.domain.DriverDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMapper {
    public Driver mapToDriver(final DriverDto driverDto) {
        return new Driver(
                driverDto.getId(),
                driverDto.getName(),
                driverDto.getSurname()
        );
    }

    public DriverDto mapToDriverDto(final Driver driver) {
        return new DriverDto(
                driver.getId(),
                driver.getName(),
                driver.getSurname()
        );
    }

    public List<DriverDto> mapToDriverDtoList(final List<Driver> driverList) {
        return driverList.stream()
                .map(this::mapToDriverDto)
                .toList();
    }
}
