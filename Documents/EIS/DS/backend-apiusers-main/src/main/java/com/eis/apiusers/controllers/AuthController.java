package com.eis.apiusers.controllers;

import com.eis.apiusers.configs.jwt.JwtProvider;
import com.eis.apiusers.dto.CreateUserDto;
import com.eis.apiusers.dto.JwtTokenDto;
import com.eis.apiusers.dto.LoginUserDto;
import com.eis.apiusers.dto.MessageDto;
import com.eis.apiusers.entities.UserEntity;
import com.eis.apiusers.exceptions.AttributeException;
import com.eis.apiusers.services.impl.UserDetailsServiceImpl;
import com.eis.apiusers.services.impl.UserEntityServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserEntityServiceImpl userEntityService;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    @ApiOperation("register a person in the application as a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Email already in use"),
    })
    public ResponseEntity<MessageDto> register(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.saveUser(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "user " + userEntity.getUsername() + " have been created"));
    }


    @PostMapping("/login")
    @ApiOperation("login a person in the application as a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Bad Credentials"),
    })
    public ResponseEntity<JwtTokenDto> login(@ApiParam(value = "the user's email and password", required = true)
                                                 @Valid @RequestBody LoginUserDto dto) throws AttributeException {
        JwtTokenDto jwtTokenDto = userEntityService.login(dto);
        return ResponseEntity.ok(jwtTokenDto);
    }

    @PostMapping("/refresh-token")
    @ApiOperation("refresh token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
    })
    public ResponseEntity<JwtTokenDto> refreshToken(@RequestBody JwtTokenDto jwtTokenDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtTokenDto);
        JwtTokenDto jwt = new JwtTokenDto(token);
        return new ResponseEntity(jwt,HttpStatus.OK);
    }

    /*@GetMapping("/current-user")
    @ApiOperation("returned the logged-in user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public UserEntity obtainCurrentUser(Principal principal){
        return (UserEntity) userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }*/
}
