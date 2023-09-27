package com.eis.apiusers.repositories;

import com.eis.apiusers.repositories.*;
import com.eis.apiusers.services.*;
import com.eis.apiusers.entities.*;
import com.eis.apiusers.dto.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByIdProvider(Long idProvider);

    List<Contract> findByIdUserOrderByPaidDesc(Long idUser);
}

