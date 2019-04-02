package com.laisi.rabbit.rabbittest;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class RabbitTestApplication implements CommandLineRunner {

	@Autowired
	public Sender sender;



	public static void main(String[] args) {
		SpringApplication.run(RabbitTestApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		sender.sendMsg();
	}
}
