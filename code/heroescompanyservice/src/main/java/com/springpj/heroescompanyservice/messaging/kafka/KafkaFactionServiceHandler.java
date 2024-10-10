package com.springpj.heroescompanyservice.messaging.kafka;

import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaFactionServiceHandler {

    private final KafkaTemplate<String, CompanyDto> template;

    public KafkaFactionServiceHandler(KafkaTemplate<String, CompanyDto> template) {
        this.template = template;
    }

    public void onCompanyCreated(CompanyDto company){
        template.send("company-topic", company);
    }

}
