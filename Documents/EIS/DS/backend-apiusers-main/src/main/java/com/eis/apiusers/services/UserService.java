package com.eis.apiusers.services;

import com.eis.apiusers.dto.CreateUserDto;
import com.eis.apiusers.dto.JwtTokenDto;
import com.eis.apiusers.dto.LoginUserDto;
import com.eis.apiusers.dto.UserManagementDto;
import com.eis.apiusers.entities.UserEntity;
import com.eis.apiusers.exceptions.AttributeException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserEntity saveUser(CreateUserDto dto) throws AttributeException;
    public Optional<UserEntity> getUser(String email);
    public UserEntity updateUser(UserEntity userEntity) throws AttributeException;
    public void deleteUser(Long idUser);
    public JwtTokenDto login(LoginUserDto dto);
    public Optional<UserEntity> getByTokenPassword(String tokenPassword);
    public List<UserManagementDto> getAllUsers();
}
