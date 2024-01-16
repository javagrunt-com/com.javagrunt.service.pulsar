package com.javagrunt.service.pulsar;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.pulsar.core.PulsarTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ApplicationRunner runner(PulsarTemplate<String> pulsarTemplate) {
		return (args) -> pulsarTemplate.send("hello-pulsar-topic", "Hello Pulsar World!");
	}

	@PulsarListener(subscriptionName = "hello-pulsar-sub", topics = "hello-pulsar-topic")
	void listen(String message) {
		System.out.println("Message Received: " + message);
	}
}
