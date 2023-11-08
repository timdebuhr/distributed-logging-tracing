package de.tdbit.presentations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Address Service Springboot",
				version = "1.0.0",
				contact = @Contact(name = "TdB IT - Consulting", url = "https://www.tdbit.de"),
				description = "Der Adressen Service als Springboot"),
		servers = {
				@Server(url = "http://localhost:8080/workshop", description = "Springboot Workshop")
		})
@SpringBootApplication(scanBasePackages = {"de.tdbit.presentations.*"})
public class AddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressApplication.class, args);
	}

}
