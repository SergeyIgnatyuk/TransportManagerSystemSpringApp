package com.dao;

import com.model.Truck;

import java.util.List;

/**
 * DAO interface for {@link com.model.Truck}
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

public interface TruckDao {
    List<Truck> findAll();
}
