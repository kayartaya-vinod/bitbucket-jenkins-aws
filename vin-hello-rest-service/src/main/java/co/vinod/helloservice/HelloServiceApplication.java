package co.vinod.helloservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloServiceApplication {

	@GetMapping("/api/hello")
	public Map<String, String> sayHello() {
		Map<String, String> m = new HashMap<>();
		m.put("message", "Hello, world!");
		m.put("from", "Vinod KK");
		m.put("email", "vinod@vinod.co");
		return m;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloServiceApplication.class, args);
	}

}
