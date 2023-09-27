package com.eis.apiusers.controllers;

import com.eis.apiusers.dto.StateDto;
import com.eis.apiusers.services.impl.StateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateService stateService;
    @GetMapping(value = "/list")
    @ApiOperation("returns all payment methods registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Information not found")
    })
    public ResponseEntity<List<StateDto>> getAllStates() {
        List<StateDto> states = stateService.getAllStates();
        return ResponseEntity.ok(states);
    }
}