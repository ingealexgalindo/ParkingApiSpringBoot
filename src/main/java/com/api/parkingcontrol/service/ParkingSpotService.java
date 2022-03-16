package com.api.parkingcontrol.service;

import com.api.parkingcontrol.model.ParkinSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkinSpotModel save(ParkinSpotModel parkinSpotModel) {
        return parkingSpotRepository.save(parkinSpotModel);
    }
}
