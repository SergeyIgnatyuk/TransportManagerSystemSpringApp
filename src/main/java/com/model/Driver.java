package com.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Simple POJO class represents a truck
 * @author Sergey Ignatyuk
 * @version 1.0
 **/

@ApiModel(value="Driver", description="Driver of company")
public class Driver {

    @ApiModelProperty(notes = "The database generated driver ID")
    private Long id;

    @ApiModelProperty(notes = " Full name of a driver")
    private String fullName;

    private Truck truck;

    public Driver() {
    }
}
