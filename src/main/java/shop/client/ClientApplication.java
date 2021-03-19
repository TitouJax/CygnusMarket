package shop.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
@RestController
class Application{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
