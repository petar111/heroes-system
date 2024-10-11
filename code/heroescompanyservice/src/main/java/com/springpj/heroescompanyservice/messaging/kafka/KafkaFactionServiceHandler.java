package com.springpj.heroescompanyservice.messaging.kafka;

import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.model.dto.FactionDto;
import com.springpj.heroescompanyservice.repository.CompanyRepository;
import com.springpj.heroescompanyservice.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KafkaFactionServiceHandler {

    private static final Logger log = LoggerFactory.getLogger(KafkaFactionServiceHandler.class);
    private final KafkaTemplate<String, CompanyDto> template;

    private final CompanyRepository companyRepository;

    public KafkaFactionServiceHandler(KafkaTemplate<String, CompanyDto> template, CompanyRepository companyRepository) {
        this.template = template;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void onCompanyCreated(CompanyDto company){

        log.info("Company created {}. Id: {} ", company.getName(), company.getId());
        
        log.info("Sending to {} - START" , "company-created-topic");
        template.send("company-created-topic", company);
        log.info("Sending to {} - DONE" , "company-created-topic");
    }



    @KafkaListener(id = "heroes-company-service-group", topics = "faction-deleted-topic")
    @Transactional
    public void factionDeletedHandler(FactionDto faction) {
        log.info("Faction deleted {}. Id: {} ", faction.getName(), faction.getId());
        log.info("Deleting all companies by the deleted faction. - START");
        companyRepository.deleteAllByFactionId(faction.getId());
        log.info("Deleting all companies by the deleted faction. - DONE");
    }

}
