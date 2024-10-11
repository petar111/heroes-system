package com.springpj.heroesfactionservice.messaging.kafka;

import com.springpj.heroesfactionservice.model.dto.CompanyDto;
import com.springpj.heroesfactionservice.model.dto.FactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KafkaCompanyServiceHandler {

    private static final Logger log = LoggerFactory.getLogger(KafkaCompanyServiceHandler.class);
    private final KafkaTemplate<String, CompanyDto> companyTemplate;
    private final KafkaTemplate<String, FactionDto> factionTemplate;
    public KafkaCompanyServiceHandler(KafkaTemplate<String, CompanyDto> companyTemplate, KafkaTemplate<String, FactionDto> factionTemplate) {
        this.companyTemplate = companyTemplate;
        this.factionTemplate = factionTemplate;
    }

    @KafkaListener(id = "heroes-company-service-group", topics = "company-topic")
    public void listen(CompanyDto company) {
        log.info("Company created: " + company.getName());
    }

    @Transactional
    public void onFactionDeleted(FactionDto deletedFaction){
        this.factionTemplate.send("faction-deleted-topic", deletedFaction);
    }

}
