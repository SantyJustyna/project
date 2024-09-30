package com.crud.project.mapper;

import com.crud.project.domain.Vehicle;
import com.crud.project.domain.VehicleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMapper {
    public Vehicle mapToVehicle(final VehicleDto vehicleDto) {
        return new Vehicle(
                vehicleDto.getId(),
                vehicleDto.getPlateNumber(),
                vehicleDto.getType(),
                vehicleDto.getNextInspectionDate()
        );
    }

    public VehicleDto mapToVehicleDto(final Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getPlateNumber(),
                vehicle.getType(),
                vehicle.getNextInspectionDate()
        );
    }

    public List<VehicleDto> mapToVehicleDtoList(final List<Vehicle> vehicleList) {
        return vehicleList.stream()
                .map(this::mapToVehicleDto)
                .toList();
    }
}