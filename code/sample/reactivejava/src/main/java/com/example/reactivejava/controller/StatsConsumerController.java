package com.example.reactivejava.controller;

import com.example.reactivejava.model.StatsMessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/stats")
public class StatsConsumerController {

    @GetMapping("/message")
    public Mono<StatsMessageDto> statsMessage(){

        WebClient client = WebClient.create("http://stats-producer:5000");
        return client.get().uri("/hello/message")
                .exchangeToMono(r -> r.bodyToMono(StatsMessageDto.class));
    }

    @GetMapping("/sampledata/integer/{n}")
    public Mono<List<Integer>> sampleIntegers(@PathVariable int n){
        Random r = new Random();

        List<Integer> result = new ArrayList<>(2000);

        for(int i = 0; i < n; i++){
            result.add(r.nextInt(1000));
        }

        return Mono.just(result);

    }

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Hello");
    }
}
