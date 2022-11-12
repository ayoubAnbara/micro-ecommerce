package com.anbaralabs;

import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ayoub Anbara
 */
@Configuration
public class Beans {
   /* @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        // configuration static  based on IP Addresses
     *//*  return builder.routes()
                .route(r -> r.path("/customers/**").uri("http://localhost:8081/"))
                .route(r -> r.path("/products/**").uri("http://localhost:8082/"))
                .build();
                *//*
        // configuration static based on services names
        return builder.routes()
                .route(r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE")) // lb ==> load balancer
                .route(r -> r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .build();
    }*/

    // configuration dynamic based on services names that should b exists in url
    // ex: http://localhost:8083/CUSTOMER-SERVICE/customers
    @Bean
    public DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
            ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
    }
}
