package com.dao;

import com.model.Driver;

import java.util.List;

/**
 * DAO interface for {@link com.model.Driver}
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

public interface DriverDao {
    List<Driver> findAll();
}
