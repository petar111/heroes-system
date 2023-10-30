package com.springpj.heroescontentcreator.application.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GlobalApplicationInitializationListener {

	private static final Logger log = LoggerFactory.getLogger(GlobalApplicationInitializationListener.class);
	

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("info");
		
	}

}
