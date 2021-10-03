package com.service;

import com.dao.DriverDao;
import com.exception.ResourceNotFoundException;
import com.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link DriverService} interface.
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverDao driverDao;

    @Autowired
    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true)
    public List<Driver> getAllDrivers() {
        return driverDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Driver getOneDriverById(Long id) {
        return driverDao.getOne(id).orElseThrow(() -> new ResourceNotFoundException("Driver with ID: " + id + " Not Found!"));
    }
}
