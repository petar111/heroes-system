package com.springpj.heroessimulationservice.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.springpj.heroessimulationservice.client.BattleTypeClientProxy;
import com.springpj.heroessimulationservice.client.EntityClientProxy;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationRequestDto;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.*;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class SimpleBattleSimulationTest {

	Gson gson = new Gson();
	@Mock
	SimulationService mockSimulationService;
	@MockBean
	BattleTypeClientProxy battleTypeClientProxy;
	@MockBean
	EntityClientProxy entityClientProxy;


	@Autowired
	SimulationService simulationService;


	@Test
	void contextLoads() {
	}

	@Test
	void testSimulationService(){

		Mono<BattleSimulationResponseDto> response = Mono.just(new BattleSimulationResponseDto());
		Mockito.when(mockSimulationService.handleOneOnOneBattle(any())).thenReturn(response);

		Assert.isTrue(mockSimulationService.handleOneOnOneBattle(new SimpleBattleSimulationRequestDto()).equals(response), "Test failed.");
	}

	@Test
	void testHandleSimpleBattle() throws IOException {

		JsonReader knightReader = new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/data/creature-knight.json"))));
		JsonReader peasantReader = new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/data/creature-peasant.json"))));

		EntityDefinitionDto knight = gson.fromJson(knightReader, EntityDefinitionDto.class);
		EntityDefinitionDto peasant = gson.fromJson(peasantReader, EntityDefinitionDto.class);

		Mockito.when(battleTypeClientProxy.getCapacitiesByEntityId(1L)).thenReturn(Mono.just(knight.getBattleCapacities()));
		Mockito.when(battleTypeClientProxy.getCapacitiesByEntityId(2L)).thenReturn(Mono.just(peasant.getBattleCapacities()));

		SimpleBattleSimulationRequestDto requestDto = new SimpleBattleSimulationRequestDto();
		requestDto.setEntity1(knight);
		requestDto.setEntity2(peasant);
		requestDto.setBattleTypeIdForEntity1(1L);
		requestDto.setBattleTypeIdForEntity2(1L);

		Mono<BattleSimulationResponseDto> result = simulationService.handleOneOnOneBattle(requestDto);

		FileWriter writer = new FileWriter("battle-log.txt");


		Assert.notNull(result, "Handle battle result is not null");
		StepVerifier.create(result.map(r -> (SimpleBattleSimulationResponseDto) r)
						.doOnNext(r -> {
							try {
								writer.write(r.getBattleLog());
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
						})
						.map(r -> r.getBattleLog().contains("Battle finished.")))
				.expectNext(true)
				.verifyComplete();

		writer.close();

	}

}
