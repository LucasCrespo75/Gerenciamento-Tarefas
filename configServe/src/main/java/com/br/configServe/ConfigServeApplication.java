package com.br.configServe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServeApplication.class, args);
	}

}
