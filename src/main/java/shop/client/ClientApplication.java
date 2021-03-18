package shop.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@GetMapping(value = "/")
	public String hello(){
		return "hello";
	}
}
