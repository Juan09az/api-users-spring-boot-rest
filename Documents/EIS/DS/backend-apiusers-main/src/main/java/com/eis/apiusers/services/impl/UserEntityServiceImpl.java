package com.eis.apiusers.services.impl;

import com.eis.apiusers.configs.jwt.JwtProvider;
import com.eis.apiusers.dto.CreateUserDto;
import com.eis.apiusers.dto.JwtTokenDto;
import com.eis.apiusers.dto.LoginUserDto;
import com.eis.apiusers.dto.UserManagementDto;
import com.eis.apiusers.entities.UserEntity;
import com.eis.apiusers.exceptions.AttributeException;
import com.eis.apiusers.repositories.UserEntityRepository;
import com.eis.apiusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserEntityServiceImpl implements UserService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserEntity saveUser(CreateUserDto dto) throws AttributeException {
        if(userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Email already in use");
        return userEntityRepository.save(mapUserFromDto(dto));
    }

    @Override
    public Optional<UserEntity> getUser(String email) {
        return userEntityRepository.findByEmail(email);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) throws AttributeException {
        return userEntityRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long idUser) {
        userEntityRepository.deleteById(idUser);
    }

    public JwtTokenDto login(LoginUserDto dto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }

    @Override
    public Optional<UserEntity> getByTokenPassword(String tokenPassword) {
        return userEntityRepository.findByTokenPassword(tokenPassword);
    }

    @Override
    public List<UserManagementDto> getAllUsers() {
        List<UserEntity> users = userEntityRepository.findAll();
        List<UserManagementDto> userDTOs = new ArrayList<>();

        for (UserEntity userEntity : users) {
            UserManagementDto userDTO = new UserManagementDto();
            userDTO.setIdUser(userEntity.getIdUser());
            userDTO.setName(userEntity.getName());
            userDTO.setLastname(userEntity.getLastname());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setPhone(userEntity.getPhone());
            userDTO.setEnabled(userEntity.isEnabled());
            userDTO.setIdState(userEntity.getIdState());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    // private methods
    private UserEntity mapUserFromDto(CreateUserDto dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        return new UserEntity(dto.getName(),dto.getLastname(),dto.getEmail(),dto.getPhone(),password,true,dto.getIdState());
    }


}
