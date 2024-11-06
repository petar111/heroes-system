package com.example.reactivejava.controller;

import com.example.reactivejava.model.StatsMessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stats")
public class StatsConsumerController {

    @GetMapping("/message")
    public Mono<StatsMessageDto> statsMessage(){

        WebClient client = WebClient.create("http://stats-producer:5000/");
        return client.get().exchangeToMono(r -> r.bodyToMono(StatsMessageDto.class));
    }

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Hello");
    }
}
