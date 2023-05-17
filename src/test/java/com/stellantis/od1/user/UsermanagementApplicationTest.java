package com.stellantis.od1.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stellantis.od1.user.testdata.NamingConfig;

@Profile("test")
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsermanagementApplication.class)
@Configuration
public class UsermanagementApplicationTest extends NamingConfig {
	@Test
	void contextLoads() {

	}

}