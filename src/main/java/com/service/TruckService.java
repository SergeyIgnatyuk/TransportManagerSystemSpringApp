package com.service;

import com.model.Truck;

import java.util.List;

/**
 * Service interface for {@link com.model.Truck}
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

public interface TruckService {
    List<Truck> getAllTrucks();

    Truck getOTruckById(Long id);
}
