package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.model.ParkinSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository  extends JpaRepository<ParkinSpotModel, Long> {

    //Validar que no exista previamente licence plate car
    boolean existsByLicensePlateCar(String licencePlateCar);

    //validar que no existe preciamente parking spot number
    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    //validar que no exista apartamento y bloque
    boolean existsByApartmentAndBlock(String apartment, String block);

}
