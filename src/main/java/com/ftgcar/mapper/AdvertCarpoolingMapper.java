package com.ftgcar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.entity.AdvertCarpooling;

@Mapper(componentModel = "spring", uses = { BookingAdvertCarpoolingMapper.class, EmployeeMapper.class })
public interface AdvertCarpoolingMapper {

    AdvertCarpooling advertCarpoolingDtoToAdvertCarpooling(AdvertCarpoolingDto advertCarpoolingDto);

    AdvertCarpoolingDto advertCarpoolingToAdvertCarpoolingDto(AdvertCarpooling advertCarpooling);

    AdvertCarpooling updateAdvertCarpooling(AdvertCarpoolingDto advertCarpoolingDto,
            @MappingTarget AdvertCarpooling advertCarpoolingToUpdate);

}
