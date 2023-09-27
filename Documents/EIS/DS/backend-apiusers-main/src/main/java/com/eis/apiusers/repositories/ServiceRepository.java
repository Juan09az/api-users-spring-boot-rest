package com.eis.apiusers.repositories;

import com.eis.apiusers.entities.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceUser, Long> {
}
