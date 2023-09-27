package com.eis.apiusers.services;

import com.eis.apiusers.dto.ReceiveContractDto;
import com.eis.apiusers.dto.UsersByProviderDto;
import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.exceptions.AttributeException;

import java.util.List;
import java.util.Optional;

public interface ContractService {

    List<UsersByProviderDto> getContractsByProvider(Long idProvider, String apiKey);
   void updateContracts(List<ReceiveContractDto> contracts);
}
