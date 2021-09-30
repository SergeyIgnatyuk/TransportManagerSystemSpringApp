package com.service;

import com.model.Driver;

import java.util.List;

/**
 * Service interface for {@link com.model.Driver}
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

public interface DriverService {
    List<Driver> getAllDrivers();
}
