package com.stackroute.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRouteLocater(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r->r.path("/api/v1/user/**")
						.uri("http://localhost:5555"))
				.route(r->r.path("/user/**")
						.uri("http://localhost:1122"))
				.route(r->r.path("/campaigns/**")
						.uri("http://localhost:8085"))
				.route(r->r.path("/pg/**")
						.uri("http://localhost:5566"))
				.route(r->r.path("/paymentsave/**")
						.uri("http://localhost:5566"))
				.route(r->r.path("/paymentemail/**")
						.uri("http://localhost:5566"))
				.route(r->r.path("/chatgpt/**")
						.uri("http://localhost:8999"))
				.route(r->r.path("/email/**")
						.uri("http://localhost:8888"))
				.route(r->r.path("/api/v1/**")
						.uri("http://localhost:8089"))
				.route(r->r.path("/**")
						.uri("lb://PRODUCT-WEBAPP"))
				.build();
	}


}
