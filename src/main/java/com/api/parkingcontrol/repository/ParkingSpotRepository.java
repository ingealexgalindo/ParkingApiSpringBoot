package com.api.parkingcontrol.repository;

import com.api.parkingcontrol.model.ParkinSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository  extends JpaRepository<ParkinSpotModel, Long> {

}
