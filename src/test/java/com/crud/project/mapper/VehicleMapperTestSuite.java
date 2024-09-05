package com.crud.project.mapper;

import com.crud.project.domain.Vehicle;
import com.crud.project.domain.VehicleDto;
import net.bytebuddy.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class VehicleMapperTestSuite {
    @Autowired
    private VehicleMapper mapper;

    @Test
    public void shouldMapToVehicle() {
        //Given
        VehicleDto vehicleDto = new VehicleDto(22L, "GDA222", "mega", LocalDate.of(2024, 11, 1));

        //When
        Vehicle vehicle = mapper.mapToVehicle(vehicleDto);

        //Then
        assertEquals("mega", vehicle.getType());
    }

    @Test
    public void shouldMapToVehicleDto() {
        //Given
        Vehicle vehicle = new Vehicle(22L, "GDA222", "mega", LocalDate.of(2024, 11, 1));

        //When
        VehicleDto vehicleDto = mapper.mapToVehicleDto(vehicle);

        //Then
        assertEquals("GDA222", vehicleDto.getPlateNumber());
    }

    @Test
    public void shouldMapToVehicleDtoList() {
        //Given
        Vehicle vehicle1 = new Vehicle(22L, "GDA222", "mega", LocalDate.of(2024, 11, 1));
        Vehicle vehicle2 = new Vehicle(44L, "GDA444", "mega", LocalDate.of(2024, 11, 1));
        List<Vehicle> vehicleList = List.of(vehicle1, vehicle2);

        //When
        List<VehicleDto> vehicleDtoList = mapper.mapToVehicleDtoList(vehicleList);

        //Then
        assertEquals(2, vehicleDtoList.size());
        assertEquals("GDA444", vehicleDtoList.get(1).getPlateNumber());
    }
}
