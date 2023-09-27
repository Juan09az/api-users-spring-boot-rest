package com.eis.apiusers.controllers;

import com.eis.apiusers.dto.UserManagementDto;
import com.eis.apiusers.services.impl.UserEntityServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntityController {
    @Autowired
    private UserEntityServiceImpl userEntityServiceImpl;

    @GetMapping()
    @ApiOperation("the server returns all registered users (user id, first name, last name, email, mobile, if the user is enabled and department id).")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Unauthorized"),
    })
    public List<UserManagementDto> getAllUsers() {
        return userEntityServiceImpl.getAllUsers();
    }
}
