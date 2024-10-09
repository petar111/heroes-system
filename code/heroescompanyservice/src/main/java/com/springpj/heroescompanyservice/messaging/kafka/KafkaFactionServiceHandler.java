package com.springpj.heroescompanyservice.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaFactionServiceHandler {

    private final KafkaTemplate<String, String> template;

    public KafkaFactionServiceHandler(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void onCompanyCreated(String createCompanyName){
        template.send("company-topic", createCompanyName);
    }

}
