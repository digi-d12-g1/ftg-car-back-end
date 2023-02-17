package com.ftgcar.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ftgcar.dao.AdvertCarpoolingRepository;
import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.dto.BookingAdvertCarpoolingDto;
import com.ftgcar.dto.EmployeeDto;
import com.ftgcar.entity.AdvertCarpooling;
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

    public List<AdvertCarpoolingDto> findAllAdvertCarpoolingsBetweenDates(Date date) {
        return advertCarpoolingRepository
                .findAllByDepartureEqual(date)
                .stream()
                .map(advertCarpoolingMapper::advertCarpoolingToAdvertCarpoolingDto)
                .toList();
    }

    public AdvertCarpoolingDto addAdvertCarpooling(AdvertCarpoolingDto advertCarpoolingDto) {
        advertCarpoolingRepository
                .save(advertCarpoolingMapper.advertCarpoolingDtoToAdvertCarpooling(advertCarpoolingDto));
        return advertCarpoolingDto;
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

    public void deleteAdvertCarpooling(Long id) throws NotFoundException {
        findAdvertCarpoolingById(id);
        advertCarpoolingRepository.deleteById(id);
    }

    public AdvertCarpoolingDto updateAdvertCarpooling(AdvertCarpoolingDto advertCarpoolingDto) {
        Optional<AdvertCarpooling> existingAdvertCarpooling = advertCarpoolingRepository
                .findById(advertCarpoolingDto.id());
        if (existingAdvertCarpooling.isPresent()) {
            advertCarpoolingMapper.updateAdvertCarpooling(advertCarpoolingDto, existingAdvertCarpooling.get());
        }
        return advertCarpoolingDto;
    }

    public void bookASeat(AdvertCarpoolingDto existingAdvertCarpooling) {
        Optional<AdvertCarpooling> advertCarpooling = advertCarpoolingRepository.findById(existingAdvertCarpooling.id());
        if(advertCarpooling.isPresent()) {
            advertCarpooling.get().setSeatAvailable((short) (advertCarpooling.get().getSeatAvailable() - 1));
            advertCarpoolingRepository.save(advertCarpooling.get());
        }
    }

    public void freeUpASeat(BookingAdvertCarpoolingDto bookingAdvertCarpoolingDto) {
        AdvertCarpooling advertCarpooling = bookingAdvertCarpoolingDto.idAdvertCarpooling();
        advertCarpooling.setSeatAvailable((short) (advertCarpooling.getSeatAvailable() + 1));
        advertCarpoolingRepository.save(advertCarpooling);
    }

}
