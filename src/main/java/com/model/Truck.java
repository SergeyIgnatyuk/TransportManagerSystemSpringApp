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

@ApiModel(value = "Truck", description = "Truck of company")
public class Truck {

    @ApiModelProperty(notes = "The database generated truck ID")
    private Long id;

    @ApiModelProperty(notes = "Name of the truck")
    private String name;

    @ApiModelProperty(notes = "The registration number of the truck")
    private String registrationNumber;

    private Driver driver;

    public Truck() {
    }

    public Truck(Long id, String model, String registrationNumber, Driver driver) {
        this.id = id;
        this.name = model;
        this.registrationNumber = registrationNumber;
        this.driver = driver;
    }

    public Truck(String model, String registrationNumber, Driver driver) {
        this.name = model;
        this.registrationNumber = registrationNumber;
        this.driver = driver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return Objects.equals(id, truck.id) && Objects.equals(name, truck.name) && Objects.equals(registrationNumber, truck.registrationNumber) && Objects.equals(driver, truck.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, registrationNumber, driver);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", driver=" + driver +
                '}';
    }

    public static TruckBuilder builder() {
        return new Truck().new TruckBuilder();
    }

    public class TruckBuilder {

        private TruckBuilder() {

        }

        public TruckBuilder id(Long id) {

            Truck.this.id = id;

            return this;
        }

        public TruckBuilder name(String name) {

            Truck.this.name = name;

            return this;
        }

        public TruckBuilder registrationNumber(String registrationNumber) {

            Truck.this.registrationNumber = registrationNumber;

            return this;
        }

        public TruckBuilder driver(Driver driver) {

            Truck.this.driver = driver;

            return this;
        }

        public Truck build() {
            return Truck.this;
        }
    }
}
