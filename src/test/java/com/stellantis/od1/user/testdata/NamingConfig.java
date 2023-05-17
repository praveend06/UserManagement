package com.stellantis.od1.user.testdata;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

public class NamingConfig {
	@TestConfiguration
	static class Config {
		@Bean
		public HibernatePropertiesCustomizer customizer() {
			return (HibernatePropertiesCustomizer) new HibernateConfig();
		}
	}
}
