package com.springpj.heroesfactionservice.messaging.kafka;

import com.springpj.heroesfactionservice.model.dto.CompanyDto;
import com.springpj.heroesfactionservice.model.dto.FactionDto;
import com.springpj.heroesfactionservice.service.FactionService;
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
    private final FactionService factionService;

    public KafkaCompanyServiceHandler(KafkaTemplate<String, CompanyDto> companyTemplate, KafkaTemplate<String, FactionDto> factionTemplate, FactionService factionService) {
        this.companyTemplate = companyTemplate;
        this.factionTemplate = factionTemplate;
        this.factionService = factionService;
    }

    @KafkaListener(id = "heroes-company-service-group", topics = "company-created-topic")
    @Transactional
    public void listen(CompanyDto company) {
        log.info("Company created: {} Id: {}", company.getName(), company.getId());

        log.info("Checking if there is faction with the selected id: {} - START", company.getFactionId());

        FactionDto faction = factionService.findById(company.getFactionId());

        log.info("Faction with id {} found. Faction name: {}", faction.getId(), faction.getName());
        log.info("Checking if there is faction with the selected id: {} - START", company.getFactionId());
    }

    @Transactional
    public void onFactionDeleted(FactionDto deletedFaction){

        log.info("Faction deleted: {}, Id: {}" ,deletedFaction.getName(), deletedFaction.getId());
        log.info("Sending to {} - START" , "faction-deleted-topic");
        this.factionTemplate.send("faction-deleted-topic", deletedFaction);
        log.info("Sending to {} - DONE" , "faction-deleted-topic");

    }

}
