package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.UpdateContractPersonalizedDto;
import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.entities.ServiceUser;
import com.eis.apiusers.repositories.ContractRepository;
import com.eis.apiusers.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

    @Service
    public class UpdateContractPersonalizedServiceImpl {
        @Autowired
        private ContractRepository contractRepository;

        @Autowired
        private ServiceRepository serviceRepository;

        public void updateContract(UpdateContractPersonalizedDto contractDto) {
            Optional<Contract> optionalContract = contractRepository.findById(contractDto.getIdContract());

            if (optionalContract.isPresent()) {
                Contract contract = optionalContract.get();

                // Actualizar los campos del contrato
                contract.setNotificationSetting(contractDto.getNotificationSetting());
                contract.setValue(contractDto.getValue());
                contract.setDueDate(contractDto.getDueDate());
                contract.setDescription(contractDto.getDescription());

                // Actualizar los campos del servicio si existen
                if (contractDto.getIdService() != null) {
                    Optional<ServiceUser> optionalService = serviceRepository.findById(contractDto.getIdService());
                    if (optionalService.isPresent()) {
                        ServiceUser service = optionalService.get();
                        service.setNameService(contractDto.getNameService());
                        service.setFrequency(contractDto.getFrequency());
                        serviceRepository.save(service);
                    }
                }

                // Guardar los cambios en la base de datos
                contractRepository.save(contract);
            }
        }
    }

