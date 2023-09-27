package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.ReceiveContractDto;
import com.eis.apiusers.dto.UsersByProviderDto;
import com.eis.apiusers.services.*;
import com.eis.apiusers.repositories.ContractRepository;
import com.eis.apiusers.entities.*;

import java.util.*;
import com.eis.apiusers.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService{
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ProviderRepository providerRepository;
    public List<UsersByProviderDto> getContractsByProvider(Long idProvider, String apiKey) {
            Optional<Provider> providerOpt = providerRepository.findByIdProviderAndApiKey(idProvider, apiKey);
            if (providerOpt.isPresent()) {
                List<Contract> contracts = contractRepository.findByIdProvider(idProvider);
                List<UsersByProviderDto> dtos = new ArrayList<>();

                for (Contract contract : contracts) {
                    UsersByProviderDto dto = new UsersByProviderDto(
                            contract.getIdContract(),
                            contract.getUserEntity().getIdUser(),
                            contract.getUserEntity().getEmail(),
                            contract.getUserEntity().getPhone(),
                            contract.getNotificationSetting(),
                            contract.getSubscriptionCode(),
                            contract.getUserEntity().getName(),
                            contract.getUserEntity().getLastname()
                    );
                    dtos.add(dto);
                }
                if (dtos.isEmpty()) {
                    throw new IllegalArgumentException("Ningún contrato encontrado");
                }
                return dtos;
            } else {
                throw new IllegalArgumentException("La apiKey no corresponde al idProvider");
            }
        }

    @Override
    public void updateContracts(List<ReceiveContractDto> receiveContractDtos) {
        Optional<Provider> providerOpt = providerRepository.findByIdProviderAndApiKey(receiveContractDtos.get(0).getId_provider(), receiveContractDtos.get(0).getApiKey());
        if (providerOpt.isPresent()) {
            List<Contract> contracts = new ArrayList<>();

            for (ReceiveContractDto receiveContractDto : receiveContractDtos) {
                Contract contract = new Contract(
                        receiveContractDto.getId_contract(),
                        receiveContractDto.getId_provider(),
                        receiveContractDto.getValue(),
                        receiveContractDto.getDue_date(),
                        receiveContractDto.getNotification_setting()
                );
                contracts.add(contract);
            }
            if (contracts.isEmpty()){
                throw new IllegalArgumentException("Ningún contrato encontrado");
            }
            contractRepository.saveAll(contracts);
        }else {
            throw new IllegalArgumentException("La apiKey no corresponde al idProvider");
        }
    }


}