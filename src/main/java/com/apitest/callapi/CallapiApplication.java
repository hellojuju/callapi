package com.apitest.callapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CallapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallapiApplication.class, args);
		log.debug("debug----");
		log.info("info---");
	}

}
