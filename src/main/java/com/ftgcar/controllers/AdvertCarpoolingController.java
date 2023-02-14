package com.ftgcar.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ftgcar.dto.AdvertCarpoolingDto;
import com.ftgcar.dto.DatesDto;
import com.ftgcar.exception.NotFoundException;
import com.ftgcar.services.AdvertCarpoolingService;

@CrossOrigin
@RestController
@RequestMapping("/api/advert-carpoolings")
public class AdvertCarpoolingController {

    private final AdvertCarpoolingService advertCarpoolingService;

    @Autowired
    public AdvertCarpoolingController(AdvertCarpoolingService advertCarpoolingService) {
        this.advertCarpoolingService = advertCarpoolingService;
    }

    @GetMapping("/findAllBetweenDates")
    public List<AdvertCarpoolingDto> findAllAdvertCarpoolingsBetweenDates(@RequestBody DatesDto datesDto) {
        return advertCarpoolingService.findAllAdvertCarpoolingsBetweenDates(datesDto);
    }

    @GetMapping("/findOpenedAdverts/{idEmployee}")
    public List<AdvertCarpoolingDto> findAllAdvertCarpoolingOpenedByEmployeeId(@PathVariable Long idEmployee)
            throws NotFoundException {
        return advertCarpoolingService.findAllAdvertCarpoolingOpenedByEmployeeId(idEmployee);
    }

    @GetMapping("/find/{id}")
    public AdvertCarpoolingDto findAdvertCarpoolingById(@PathVariable Long id) throws NotFoundException {
        return advertCarpoolingService.findAdvertCarpoolingById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AdvertCarpoolingDto addAdvertCarpooling(@RequestBody AdvertCarpoolingDto advertCarpoolingDto) {
        return advertCarpoolingService.addAdvertCarpooling(advertCarpoolingDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAdvertCarpooling(@PathVariable Long id) throws NotFoundException {
        advertCarpoolingService.deleteAdvertCarpooling(id);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public AdvertCarpoolingDto updateAdvertCarpooling(@RequestBody AdvertCarpoolingDto advertCarpoolingDto) {
        return advertCarpoolingService.updateAdvertCarpooling(advertCarpoolingDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
