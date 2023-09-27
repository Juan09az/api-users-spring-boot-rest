package com.eis.apiusers.repositories;

import com.eis.apiusers.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository  extends JpaRepository<Provider,Long> {
    Optional<Provider> findByIdProviderAndApiKey(Long idProvider, String apiKey);
}