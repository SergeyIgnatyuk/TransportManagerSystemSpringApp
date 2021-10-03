package com.controller;

import com.model.Driver;
import com.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Controller for {@link com.model.Driver}'s resources.
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/drivers")
@Validated
@Api(value="management system", tags="Operations on drivers")
public class DriverRestController {

    private final DriverService driverService;

    @Autowired
    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of drivers with their trucks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View one driver by ID with his truck")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved driver"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<Driver> getOneDriverById(@PathVariable @Min(value = 1, message = "must be greater than or equal to 1") Long id) {
        return new ResponseEntity<>(driverService.getOneDriverById(id), HttpStatus.OK);
    }
}
