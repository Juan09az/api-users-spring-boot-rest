package com.eis.apiusers.services.impl;

import com.eis.apiusers.entities.UserEntity;
import com.eis.apiusers.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(email);
        if(!userEntity.isPresent()){
            throw new UsernameNotFoundException("User doesn't exist");
        }
        return userEntity.get();
    }
}
