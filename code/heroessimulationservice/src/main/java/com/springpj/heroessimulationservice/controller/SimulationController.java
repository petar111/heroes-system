package com.springpj.heroessimulationservice.controller;

import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationRequestDto;
import com.springpj.heroessimulationservice.service.SimulationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("simulation")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/battle/one-on-one")
    private Mono<BattleSimulationResponseDto> handleOneOnOneBattle(@RequestBody SimpleBattleSimulationRequestDto request){
        return simulationService.handleOneOnOneBattle(request);
    }



    @GetMapping("test")
    private Mono<String> test(){
        return Mono.just("Test SUCCESS.");
    }
}
