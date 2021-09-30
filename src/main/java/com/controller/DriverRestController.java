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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for {@link com.model.Driver}'s resources.
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/drivers")
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
}
