package com.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Simple POJO class represents a truck
 * with a builder class inside
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 **/

@ApiModel(value = "Driver", description = "Driver of company")
public class Driver {

    @ApiModelProperty(notes = "The database generated driver ID")
    private Long id;

    @ApiModelProperty(notes = " Full name of a driver")
    private String fullName;

    private Truck truck;

    public Driver() {
    }

    public Driver(Long id, String fullName, Truck truck) {
        this.id = id;
        this.fullName = fullName;
        this.truck = truck;
    }

    public Driver(String fullName, Truck truck) {
        this.fullName = fullName;
        this.truck = truck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(fullName, driver.fullName) && Objects.equals(truck, driver.truck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, truck);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", truck=" + truck +
                '}';
    }

    public static DriverBuilder builder() {
        return new Driver().new DriverBuilder();
    }

    public class DriverBuilder {

        private DriverBuilder() {

        }

        public DriverBuilder id(Long id) {

            Driver.this.id = id;

            return this;
        }

        public DriverBuilder fullName(String fullName) {

            Driver.this.fullName = fullName;

            return this;
        }

        public DriverBuilder truck(Truck truck) {

            Driver.this.truck = truck;

            return this;
        }

        public Driver build() {
            return Driver.this;
        }
    }
}
