package com.dao.jdbc;

import com.dao.TruckDao;
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
 * Implementation {@link TruckDao} interface
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 **/

@Repository
public class JdbcTruckDaoImpl implements TruckDao {

    private static final String SELECT_ALL_TRUCKS = "SELECT t.id, t.name, t.registration_number, d.id, d.full_name " +
            "FROM trucks t LEFT JOIN drivers d ON t.id = d.truck_id";

    private static final String SELECT_ONE_TRUCK = "SELECT t.id, t.name, t.registration_number, d.id, d.full_name " +
            "FROM trucks t LEFT JOIN drivers d ON t.id = d.truck_id " +
            "WHERE t.id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcTruckDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Truck> findAll() {
        return namedParameterJdbcTemplate.query(SELECT_ALL_TRUCKS, ((resultSet, i) -> Truck.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .registrationNumber(resultSet.getString("registration_number"))
                .driver(Driver.builder()
                        .id(resultSet.getLong("id"))
                        .fullName(resultSet.getString("full_name"))
                        .build())
                .build()));
    }

    @Override
    public Optional<Truck> getOne(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        Truck truck = namedParameterJdbcTemplate.queryForObject(SELECT_ONE_TRUCK, params, ((resultSet, i) -> Truck.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .registrationNumber(resultSet.getString("registration_number"))
                .driver(Driver.builder()
                        .id(resultSet.getLong("id"))
                        .fullName(resultSet.getString("full_name"))
                        .build())
                .build()));

        return Optional.ofNullable(truck);
    }
}
