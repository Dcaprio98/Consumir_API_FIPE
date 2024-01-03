package br.com.daniel.CarPriceRequest;

import br.com.daniel.CarPriceRequest.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarPriceRequestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarPriceRequestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Principal principal = new Principal();
		principal.menu();
	}
}
