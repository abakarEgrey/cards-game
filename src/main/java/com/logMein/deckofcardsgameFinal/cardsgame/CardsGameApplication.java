package com.logMein.deckofcardsgameFinal.cardsgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.logMein.deckofcardsgameFinal.cardsgame.services", "com.logMein.deckofcardsgameFinal.cardsgame.entities"})
public class CardsGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsGameApplication.class, args);
	}
}
