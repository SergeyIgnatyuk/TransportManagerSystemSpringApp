package com.service;

import com.dao.TruckDao;
import com.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link TruckDao} interface.
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

@Service
public class TruckServiceImpl implements TruckService {

    private final TruckDao truckDao;

    @Autowired
    public TruckServiceImpl(TruckDao truckDao) {
        this.truckDao = truckDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = true)
    public List<Truck> getAllTrucks() {
        return truckDao.findAll();
    }
}
