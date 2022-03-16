package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.model.ParkinSpotModel;
import com.api.parkingcontrol.service.ParkingSpotService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins ="*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        ParkinSpotModel parkinSpotModel = new ParkinSpotModel();
        BeanUtils.copyProperties(parkingSpotDto,parkinSpotModel);
        parkinSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("GMT-6")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkinSpotModel));
    }


}
