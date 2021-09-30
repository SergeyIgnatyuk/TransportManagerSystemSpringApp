package com.dao.jdbc;

import com.dao.DriverDao;
import com.model.Driver;
import com.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Implementation {@link DriverDao} interface
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 **/

@Repository
public class JdbcDriverDaoImpl implements DriverDao {

    private static final String SELECT_ALL_DRIVERS = "SELECT d.id, d.full_name, t.registration_number, t.name " +
            "FROM drivers d LEFT JOIN trucks t ON d.truck_id = t.id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcDriverDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Driver> findAll() {
        return namedParameterJdbcTemplate.query(SELECT_ALL_DRIVERS, ((resultSet, i) -> Driver.builder()
                .id(resultSet.getLong("id"))
                .fullName(resultSet.getString("full_name"))
                .truck(Truck.builder()
                        .registrationNumber(resultSet.getString("registration_number"))
                        .name(resultSet.getString("name"))
                        .build())
                .build()));
    }
}
