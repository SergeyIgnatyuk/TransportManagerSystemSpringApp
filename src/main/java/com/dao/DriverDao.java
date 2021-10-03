package com.dao;

import com.model.Driver;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface for {@link com.model.Driver}
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

public interface DriverDao {
    List<Driver> findAll();

    Optional<Driver> getOne(Long id);
}
