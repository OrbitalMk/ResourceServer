package com.example.resourceserver;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ResourceserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceserverApplication.class, args);
	}

}

@RestController
@RequestMapping("/api")
class GreetingController {

	@PreAuthorize("hasAuthority('ROLE_message:read')")
	@GetMapping("/message")
	public Map<String, String> greet(@AuthenticationPrincipal Jwt jwt) {
		return Map.of("message", "hello " + jwt.getSubject());
	}

	@GetMapping("/number")
	public Map<String, Integer> number() {
		int i = 15;
		return Map.of("number", i);
	}
}
