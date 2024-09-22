package com.springpj.heroesuserservice.application.listener;

import com.springpj.heroesuserservice.context.AuthorizationContext;
import com.springpj.heroesuserservice.repository.AccessTypeRepository;
import com.springpj.heroesuserservice.repository.AuthorityRepository;
import com.springpj.heroesuserservice.repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
