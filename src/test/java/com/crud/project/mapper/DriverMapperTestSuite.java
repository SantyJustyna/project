package com.crud.project.mapper;

import com.crud.project.domain.Client;
import com.crud.project.domain.Driver;
import com.crud.project.domain.DriverDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class DriverMapperTestSuite {
    @Autowired
    private DriverMapper mapper;

    @Test
    public void shouldMapToDriver() {
        //Given
        DriverDto driverDto = new DriverDto(22L, "test name", "test surname");

        //When
        Driver driver = mapper.mapToDriver(driverDto);

        //Then
        assertEquals("test name", driver.getName());
    }

    @Test
    public void shouldMapToDriverDto() {
        //Given
        Driver driver = new Driver(22L, "test name", "test surname");

        //When
        DriverDto driverDto = mapper.mapToDriverDto(driver);

        //Then
        assertEquals("test surname", driverDto.getSurname());
    }

    @Test
    public void shouldMapToDriverDtoList() {
        //Given
        List<Driver> driverList = List.of(new Driver(13L, "name1", "surname1"),
                                          new Driver(14L, "name2", "surname2"));

        //When
        List<DriverDto> driverDtoList = mapper.mapToGroupDtoList(driverList);

        //Then
        assertEquals(2, driverDtoList.size());
        assertEquals("name2", driverDtoList.get(1).getName());
    }
}
