package com.services.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator routeLocator (RouteLocatorBuilder routeLocatorBuilder)
	{
		// configuration statique des routes
//		return routeLocatorBuilder.routes()
//				.route(r -> r.path("/customers/**").uri("http://localhost:8081/"))
//				.route(r -> r.path("/products/**").uri("http://localhost:8082/"))
//				.build();
		
		// configuration dynamique des routes en utilisant le load balancer de eureka
		return routeLocatorBuilder.routes()
				.route(r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE/"))
				.route(r -> r.path("/products/**").uri("lb://INVENTORY-SERVICE/"))
				.build();
	}

}
