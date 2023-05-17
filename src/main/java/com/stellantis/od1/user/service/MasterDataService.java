package com.stellantis.od1.user.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import com.stellantis.od1.user.domain.RoleEntity;
import com.stellantis.od1.user.repository.RolesRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MasterDataService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	RolesRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void loadData() {
		RoleEntity optionalRole = repository.findByRole("SUPERADMIN");
		if (null == optionalRole) {
			log.info("Inserting for postgres");
			ResourceDatabasePopulator resourceDatabasePopulatorForPostgres = new ResourceDatabasePopulator(true, false,
					"UTF-8", new ClassPathResource("userdata.sql"));
			resourceDatabasePopulatorForPostgres.execute(dataSource);
		}
	}
}
