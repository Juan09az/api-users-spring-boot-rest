package com.eis.apiusers.controllers;

import com.eis.apiusers.dto.*;
import com.eis.apiusers.entities.Contract;
import com.eis.apiusers.services.impl.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eis.apiusers.services.CreateContractProviderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/contract")
@CrossOrigin("*")
public class ContractController {
    @Autowired
    private ContractServiceImpl contractService;

    @PostMapping(value = "/provider")
    @ApiOperation("returns contracts by supplier")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Information not found")
    })
    public ResponseEntity<List<UsersByProviderDto>> getContractsByProvider(@RequestBody ContractsByProviderDto request) {
        Long idProvider = request.getIdProvider();
        String apiKey = request.getApikey();
        List<UsersByProviderDto> contracts = contractService.getContractsByProvider(idProvider, apiKey);
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contracts);
    }

    @Autowired
    private updateNSServiceImpl updateNSService;
/*
    @PostMapping("/updatens")
    public ResponseEntity<String> updateNotificationSetting(@RequestBody updateContractDto updateContractDto) {
        try {
            updateNSService.updateNotificationSetting(updateContractDto.getIdContract(), updateContractDto.getNotificationSetting());
            return ResponseEntity.ok("OK");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }*/

    @PatchMapping("/{idContract}/updatens")
    @ApiOperation("modifies the means of notification of each contract")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
    })
    public ResponseEntity<String> updateNotificationSetting(@PathVariable Long idContract, @RequestBody updateContractDto updateContractDto) {
        try {
            updateNSService.updateNotificationSetting(idContract, updateContractDto.getNotificationSetting());
            return ResponseEntity.ok("OK");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @Autowired
    private updatePaidServiceImpl updatePaidService;
    @Autowired
    private RegisterPaymentServiceImpl registerPaymentService;
    @PatchMapping("/{idContract}/updatepaid")
    @ApiOperation("Mark a contract as payment and (finally, save the payment method)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
    })
    public ResponseEntity<String> updatePaidStatus(@PathVariable Long idContract, @RequestBody updateContractAndRegisterPayment updateContractAndRegisterPayment) {
        try {
            updatePaidService.updatePaidStatus(idContract, updateContractAndRegisterPayment.isPaid());
            registerPaymentService.registerPayment(updateContractAndRegisterPayment);
            return ResponseEntity.ok("OK");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @Autowired
    private CreateContractProviderServiceImpl createContractProviderService;
    @PostMapping("/createContractProvider")
    @ApiOperation("Create contract of type provider")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Provider contract created"),
            @ApiResponse(code = 500, message = "Bad request"),
    })
    public ResponseEntity<String> createContractProvider(@RequestBody CreateContractProviderDto contractDto) {
        try {
            Contract createdContract = createContractProviderService.createContract(contractDto);
            return new ResponseEntity<>(createdContract.getIdContract().toString() + "Contract created", HttpStatus.CREATED);
        } catch (Exception e) {
            String errorMessage = "Error creating contract: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Autowired
    private CreateContractPersonalizedServiceImpl createContractPersonalizedService;
    @PostMapping("/createContractPersonalized")
    @ApiOperation("Create the personalized contract and service for the user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Personalized contract and service created"),
            @ApiResponse(code = 500, message = "Bad request"),
    })
    public ResponseEntity<String> createContractPersonalized(@RequestBody CreateContractPersonalizedDto dto) {
        try {
            Contract createdContract = createContractPersonalizedService.createContract(dto);
            return new ResponseEntity<>( "Contract " + createdContract.getIdContract().toString() + " and service " + createdContract.getIdService().toString() + " created", HttpStatus.CREATED);
        } catch (Exception e) {
            String errorMessage = "Error creating contract and service: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Autowired
    private UserHistoryService userHistoryService;
    @GetMapping("/historyusers/{idUser}")
    @ApiOperation("Return all contracts of a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Bad request"),
    })
    public List<UserHistoryDTO> getUserHistory(@PathVariable Long idUser) {
        return userHistoryService.getUserHistory(idUser);
    }

    @PostMapping("/update-contracts")
    @ApiOperation("Update the information of the contracts related to the provider")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update correct"),
            @ApiResponse(code = 500, message = "Bad request"),
    })
    public ResponseEntity<String> updateContracts(@RequestBody List<ReceiveContractDto> contracts) {
        try {
            // Guardar los contratos en la base de datos
            contractService.updateContracts(contracts);
            return ResponseEntity.ok("Contratos actualizados exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar los contratos.");
        }
    }

    @Autowired
    private UpdateContractPersonalizedServiceImpl updateContractPersonalizedService;
    @PostMapping("/update-personalized")
    @ApiOperation(value = "Actualizar contratos personalizados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update correct"),
            @ApiResponse(code = 500, message = "Bad request")
    })
    public ResponseEntity<String> updatePersonalizedContracts(@RequestBody UpdateContractPersonalizedDto contractDtos) {
        try {
            updateContractPersonalizedService.updateContract(contractDtos);
            return ResponseEntity.ok("Contratos actualizados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar los contratos");
        }
    }
}

