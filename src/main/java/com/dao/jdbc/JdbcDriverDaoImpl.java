package com.dao.jdbc;

import com.dao.DriverDao;
import com.model.Driver;
import com.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation {@link DriverDao} interface
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 **/

@Repository
public class JdbcDriverDaoImpl implements DriverDao {

    private static final String SELECT_ALL_DRIVERS = "SELECT d.id, d.full_name, t.id, t.name, t.registration_number " +
            "FROM drivers d LEFT JOIN trucks t ON d.truck_id = t.id";

    private static final String SELECT_ONE_DRIVER = "SELECT d.id, d.full_name, t.id, t.name, t.registration_number " +
            "FROM drivers d LEFT JOIN trucks t ON d.truck_id = t.id " +
            "WHERE d.id = :id";

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
                        .id(resultSet.getLong("id"))
                        .name((resultSet.getString("name")))
                        .registrationNumber(resultSet.getString("registration_number"))
                        .build())
                .build()));
    }

    @Override
    public Optional<Driver> getOne(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        Driver driver = namedParameterJdbcTemplate.queryForObject(SELECT_ONE_DRIVER, params, ((resultSet, i) -> Driver.builder()
                .id(resultSet.getLong("id"))
                .fullName(resultSet.getString("full_name"))
                .truck(Truck.builder()
                        .id(resultSet.getLong("id"))
                        .name((resultSet.getString("name")))
                        .registrationNumber(resultSet.getString("registration_number"))
                        .build())
                .build()));

        return Optional.ofNullable(driver);
    }
}
