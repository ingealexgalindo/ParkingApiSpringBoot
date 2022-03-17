package com.api.parkingcontrol.service;

import com.api.parkingcontrol.model.ParkinSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkinSpotModel save(ParkinSpotModel parkinSpotModel) {
        return parkingSpotRepository.save(parkinSpotModel);
    }

    public boolean existByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);

    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment,block);
    }

    public Page<ParkinSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkinSpotModel> findById(Long id){
        return  parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delete(ParkinSpotModel parkinSpotModel) {
        parkingSpotRepository.delete(parkinSpotModel);
    }
}
