package com.eis.apiusers.controllers;

import com.eis.apiusers.dto.ChangePasswordDto;
import com.eis.apiusers.dto.EmailValuesDto;
import com.eis.apiusers.dto.MessageDto;
import com.eis.apiusers.entities.UserEntity;
import com.eis.apiusers.exceptions.AttributeException;
import com.eis.apiusers.services.impl.EmailService;
import com.eis.apiusers.services.impl.UserEntityServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/email")
@RestController
@CrossOrigin("*")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserEntityServiceImpl userEntityServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;


    @PostMapping("/sendEmail-restartPassword")
    @ApiOperation("server sends an email to the user's registered email account")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Bad Credentials"),
    })
    public ResponseEntity<MessageDto> sendEmailRecoverPassword(@ApiParam(value = "the user's email", required = true)
                                                                   @RequestBody EmailValuesDto dto) throws AttributeException {
        Optional<UserEntity> userOpt = userEntityServiceImpl.getUser(dto.getMailTo());
        if (!userOpt.isPresent())
            return ResponseEntity.ok(new MessageDto(HttpStatus.BAD_REQUEST,"No existe ningun usuario con esas credenciales"));
        UserEntity userEntity = userOpt.get();
        dto.setMailFrom(mailFrom);
        dto.setMailTo(userEntity.getEmail());
        dto.setSubject("Cambio de contraseña");
        dto.setName(userEntity.getName());
        dto.setLastname(userEntity.getLastname());
        UUID uuid= UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        userEntity.setTokenPassword(tokenPassword);
        userEntityServiceImpl.updateUser(userEntity);
        emailService.sendEmailRestartPassword(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK,"Te hemos enviado un correo"));
    }

    @PostMapping("/change-password")
    @ApiOperation("server sends an email to the user's registered email account")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
    })
    public ResponseEntity<MessageDto> changePassword(@ApiParam(value = "the user's new password, repeat password and tokenPassword", required = true)
                                                         @Valid @RequestBody ChangePasswordDto dto, BindingResult bindingResult) throws AttributeException {
        if (bindingResult.hasErrors())
            return ResponseEntity.ok(new MessageDto(HttpStatus.BAD_REQUEST,"Valores mal ingresados"));
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            return ResponseEntity.ok(new MessageDto(HttpStatus.BAD_REQUEST,"Las contraseñas no coinciden"));

        Optional<UserEntity> userOpt = userEntityServiceImpl.getByTokenPassword(dto.getTokenPassword());
        UserEntity userEntity = userOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        userEntity.setPassword(newPassword);
        userEntity.setTokenPassword(null);
        userEntityServiceImpl.updateUser(userEntity);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK,"Contraseña actualizada"));
    }
}
