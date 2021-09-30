package com.dao.jdbc;

import com.dao.DriverDao;
import com.dao.TruckDao;
import com.model.Driver;
import com.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Implementation {@link TruckDao} interface
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 **/

@Repository
public class JdbcTruckDaoImpl implements TruckDao {

    private static final String SELECT_ALL_TRUCKS = "SELECT t.id, t.registration_number, t.name, d.full_name " +
            "FROM trucks t LEFT JOIN drivers d ON t.id = d.truck_id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcTruckDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Truck> findAll() {
        return namedParameterJdbcTemplate.query(SELECT_ALL_TRUCKS, ((resultSet, i) -> Truck.builder()
                .id(resultSet.getLong("id"))
                .registrationNumber(resultSet.getString("registration_number"))
                .name(resultSet.getString("name"))
                .driver(Driver.builder()
                        .fullName(resultSet.getString("full_name"))
                        .build())
                .build()));
    }
}
