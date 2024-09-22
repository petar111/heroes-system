package com.springpj.heroescontentcreator.application.listener;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.springpj.heroescontentcreator.authorization.context.AuthorizationContext;
import com.springpj.heroescontentcreator.repository.AccessTypeRepository;
import com.springpj.heroescontentcreator.repository.AuthorityRepository;
import com.springpj.heroescontentcreator.repository.ResourceRepository;

@Component
public class GlobalApplicationInitializationListener {

	private static final Logger log = LoggerFactory.getLogger(GlobalApplicationInitializationListener.class);
	
	private final AuthorityRepository authorityRepository;
	private final ResourceRepository resourceRepository;
	private final AccessTypeRepository accessTypeRepository;
	
	public GlobalApplicationInitializationListener(AuthorityRepository authorityRepository,
			ResourceRepository resourceRepository, AccessTypeRepository accessTypeRepository) {
		this.authorityRepository = authorityRepository;
		this.resourceRepository = resourceRepository;
		this.accessTypeRepository = accessTypeRepository;
	}




	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("info");
		AuthorizationContext.INSTANCE.initialzieAurhoritiesAchitecture(
				authorityRepository.findAll().stream().collect(Collectors.toMap(a -> a.getId(), a -> a)), 
				resourceRepository.findAll().stream().collect(Collectors.toMap(r -> r.getId(), r -> r)),
				accessTypeRepository.findAll().stream().collect(Collectors.toMap(a -> a.getId(), a -> a)));
		
	}

}
