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
import java.util.*;
import java.util.stream.Collectors;


@Configuration
@Profile("dev")
public class ReactiveFeignConfigurationDev {

    @Bean
    public EntityClientProxyImpl getClient(){
        Gson gson = new Gson();

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(ReactiveFeignConfigurationDev.class.getResourceAsStream("/data/creatures_mapped.json")))) {

            Map<Long, EntityDefinitionDto> entities = Arrays.stream(gson.fromJson(reader, EntityDefinitionDto[].class))
                    .collect(Collectors.toMap(EntityDefinitionDto::getId, e -> e));

            return new EntityClientProxyImpl(entities);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find files.");
        }


    }
}
