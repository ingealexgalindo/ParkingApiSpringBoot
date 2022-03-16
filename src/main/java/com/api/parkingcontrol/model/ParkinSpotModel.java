package com.api.parkingcontrol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "PARKING_SPOT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkinSpotModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long parkinSpotId;

    @Column(nullable = false, unique = true,length = 10)
    private String parkingSpotNumber;

    @Column(nullable = false,unique = true,length = 7)
    private String licensePlateCar;

    @Column(nullable = false,length = 70)
    private String brandCar;

    @Column(nullable = false,length = 70)
    private String colorCar;

    @Column(nullable = false,length = 70)
    private String modelCar;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 130)
    public String responsableName;

    @Column(nullable = false, length = 30)
    private String apartment;

    @Column(nullable = false,length = 30)
    private String block;



}
