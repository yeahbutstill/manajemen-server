package com.yeahbutstill.manageserver;

import com.yeahbutstill.manageserver.model.Server;
import com.yeahbutstill.manageserver.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static com.yeahbutstill.manageserver.enumeration.Status.SERVER_DOWN;
import static com.yeahbutstill.manageserver.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ManageserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageserverApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(
					null,
					"192.168.1.160",
					"Ubuntu Linux",
					"16 GB",
					"Personal PC",
					"http://localhost:8080/server/image/server-1.png",
					SERVER_UP
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.58",
					"Fedora Linux",
					"16 GB",
					"Dell Tower",
					"http://localhost:8080/server/image/server-2.png",
					SERVER_DOWN
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.21",
					"Manjora",
					"32 GB",
					"Web Server",
					"http://localhost:8080/server/image/server-3.png",
					SERVER_UP
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.14",
					"Red Hat Enterprise Linux",
					"64 GB",
					"Mail Server",
					"http://localhost:8080/server/image/server-4.png",
					SERVER_DOWN
			));
		};
	}

}
