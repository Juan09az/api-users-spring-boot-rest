package com.eis.apiusers.services.impl;

import com.eis.apiusers.entities.ServiceUser;
import com.eis.apiusers.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateServiceContractServiceImpl {
    @Autowired
    private ServiceRepository serviceRepository;
/*
    @Autowired
    public CreateServiceContractServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }*/

    public ServiceUser createServiceContract(Long idService, String frequency, String nameService) {
        // Crear servicio
        ServiceUser service = new ServiceUser();
        service.setIdService(idService);
        service.setFrequency(frequency);
        service.setNameService(nameService);

        // Guardar el servicio en la base de datos
        return serviceRepository.save(service);
    }
}
