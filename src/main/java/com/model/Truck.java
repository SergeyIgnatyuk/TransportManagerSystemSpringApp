package com.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Simple POJO class represents a truck
 * @author Sergey Ignatyuk
 * @version 1.0
 **/

@ApiModel(value="Truck", description="Truck of company")
public class Truck {

    @ApiModelProperty(notes = "The database generated truck ID")
    private Long id;

    @ApiModelProperty(notes = "Name of the truck")
    private String model;

    @ApiModelProperty(notes = "The registration number of the truck")
    private String registrationNumber;

    private Driver driver;

    public Truck() {
    }

}
