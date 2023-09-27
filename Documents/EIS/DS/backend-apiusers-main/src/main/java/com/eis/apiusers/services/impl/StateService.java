package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.StateDto;
import com.eis.apiusers.entities.State;
import com.eis.apiusers.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<StateDto> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(state -> {
                    StateDto dto = new StateDto();
                    dto.setIdState(state.getIdState());
                    dto.setNameState(state.getNameState());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}