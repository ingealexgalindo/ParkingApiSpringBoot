package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.model.ParkinSpotModel;
import com.api.parkingcontrol.service.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins ="*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    //Create new register
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        //Validar campos unicos
        if(parkingSpotService.existByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict Licence plate car is alreader in use");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict parking sport number is alreader in use");
        }

        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict Apartment and bloc  is alreader in use");
        }

        ParkinSpotModel parkinSpotModel = new ParkinSpotModel();
        BeanUtils.copyProperties(parkingSpotDto,parkinSpotModel);
        parkinSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("GMT-6")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkinSpotModel));
    }

    //obtener todos los registros y paginarlos
   /* @GetMapping
    public ResponseEntity<Page<ParkinSpotModel>> getAllParkingSpot(
            //paginar los resultados
            @PageableDefault( page = 0, size = 10, sort = "parkinSpotId", direction = Sort.Direction.ASC)Pageable pageable
            ){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAllP(pageable));
    }*/

    //obtener todos los registros
    @GetMapping
    public ResponseEntity<List<ParkinSpotModel>> getAllParkingSpot(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAllL());
    }

    //obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdParkingSpot(@PathVariable(value = "id") Long id){
        //primero se valida que el objeto exista
        Optional<ParkinSpotModel> parkinSpotModelOptional = parkingSpotService.findById(id);
        if (!parkinSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot ID not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkinSpotModelOptional.get());
    }

    //eliminar por ID
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteParkingSpot(@PathVariable (value = "id") Long id){
        Optional<ParkinSpotModel> parkinSpotModelOptional= parkingSpotService.findById(id);
        if(!parkinSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot ID not found.");
        }
        parkingSpotService.delete(parkinSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted successfuly");
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid ParkingSpotDto parkingSpotDto)
    {
        Optional<ParkinSpotModel>parkinSpotModelOptional = parkingSpotService.findById(id);
        if (!parkinSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
        }
        //se crea un nuevo objeto
        ParkinSpotModel parkinSpotModel = new ParkinSpotModel();
        //se copia el dto al nuevo objeto
        BeanUtils.copyProperties(parkingSpotDto,parkinSpotModel);
        //se setean los atributos que no van a cambiar
        parkinSpotModel.setParkinSpotId(parkinSpotModelOptional.get().getParkinSpotId());
        parkinSpotModel.setRegistrationDate(parkinSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkinSpotModel));
    }


}
