package com.springpj.heroessimulationservice.configuration;

import com.google.gson.Gson;
import com.springpj.heroessimulationservice.client.impl.EntityClientProxyImpl;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Configuration
@Profile("dev")
public class ReactiveFeignConfigurationDev {

    @Bean
    public EntityClientProxyImpl getClient(){
        Gson gson = new Gson();

        try (Reader readerKnight = new InputStreamReader(Objects.requireNonNull(ReactiveFeignConfigurationDev.class.getResourceAsStream("/data/creature-knight.json")));
                Reader readerPeasant = new InputStreamReader(Objects.requireNonNull(ReactiveFeignConfigurationDev.class.getResourceAsStream("/data/creature-peasant.json")))) {
            EntityDefinitionDto knight = gson.fromJson(readerKnight, EntityDefinitionDto.class);
            EntityDefinitionDto peasant = gson.fromJson(readerPeasant, EntityDefinitionDto.class);

            Map<Long, EntityDefinitionDto> entities = new HashMap<>();

            entities.put(knight.getId(), knight);
            entities.put(peasant.getId(), peasant);

            return new EntityClientProxyImpl(entities);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find files.");
        }


    }
}
