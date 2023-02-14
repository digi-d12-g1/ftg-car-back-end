package com.ftgcar.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.AdvertCarpoolingRepository;
import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.entity.AdvertCarpooling;
import com.ftgcar.entity.Employee;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.mapper.AdvertCarpoolingMapper;

@Service
@Transactional
public class AdvertCarpoolingService {

    private final AdvertCarpoolingRepository advertCarpoolingRepository;
    private final AdvertCarpoolingMapper advertCarpoolingMapper;
    private final EmployeeService employeeService;

    public AdvertCarpoolingService(AdvertCarpoolingRepository advertCarpoolingRepository,
            AdvertCarpoolingMapper advertCarpoolingMapper, EmployeeService employeeService) {
        this.advertCarpoolingRepository = advertCarpoolingRepository;
        this.advertCarpoolingMapper = advertCarpoolingMapper;
        this.employeeService = employeeService;
    }

    public List<AdvertCarpoolingDto> findAllAdvertCarpoolingsWithSeatAvailable() {
        Short zero = 0;
        return advertCarpoolingRepository
                .findAllBySeatAvailableGreaterThan(zero)
                .stream()
                .map(advertCarpoolingMapper::advertCarpoolingToAdvertCarpoolingDto)
                .toList();
    }

    public List<AdvertCarpoolingDto> addAdvertCarpooling(AdvertCarpoolingDto advertCarpoolingDto) {
        advertCarpoolingRepository
                .save(advertCarpoolingMapper.advertCarpoolingDtoToAdvertCarpooling(advertCarpoolingDto));
        return findAllAdvertCarpoolingsWithSeatAvailable();
    }

    public List<AdvertCarpoolingDto> findAllAdvertCarpoolingOpenedByEmployeeId(Long idEmployee)
            throws NotFoundException {
        EmployeeDto existingEmployee = employeeService.findEmployeeById(idEmployee);
        return advertCarpoolingRepository.findAllByIdEmployeId(existingEmployee.id())
                .stream()
                .map(advertCarpoolingMapper::advertCarpoolingToAdvertCarpoolingDto)
                .toList();
    }

    public AdvertCarpoolingDto findAdvertCarpoolingById(Long id) throws NotFoundException {
        Optional<AdvertCarpooling> existingAdvert = advertCarpoolingRepository.findById(id);
        if (existingAdvert.isEmpty()) {
            throw new NotFoundException("L'annonce de covoiturage demandée n'existe pas.");
        }
        return advertCarpoolingMapper.advertCarpoolingToAdvertCarpoolingDto(existingAdvert.get());
    }

    public List<AdvertCarpoolingDto> deleteAdvertCarpooling(Long id) throws NotFoundException {
        findAdvertCarpoolingById(id);
        advertCarpoolingRepository.deleteById(id);
        return findAllAdvertCarpoolingsWithSeatAvailable();
    }

    public List<AdvertCarpoolingDto> updateAdvertCarpooling(AdvertCarpoolingDto advertCarpoolingDto)
            throws NotFoundException {
        Optional<AdvertCarpooling> existingAdvertCarpooling = advertCarpoolingRepository
                .findById(advertCarpoolingDto.id());
        if (existingAdvertCarpooling.isPresent()) {
            advertCarpoolingMapper.updateAdvertCarpooling(advertCarpoolingDto, existingAdvertCarpooling.get());
        }
        return findAllAdvertCarpoolingsWithSeatAvailable();
    }

}
