package com.springpj.heroesfactionservice.messaging.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaCompanyServiceHandler {

    private static final Logger log = LoggerFactory.getLogger(KafkaCompanyServiceHandler.class);

    @KafkaListener(id = "heroes-company-service-group", topics = "company-topic")
    public void listen(String companyName) {
        log.info("Company created: " + companyName);
    }

}