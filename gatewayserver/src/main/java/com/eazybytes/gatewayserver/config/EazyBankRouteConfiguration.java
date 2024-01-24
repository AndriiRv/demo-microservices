package com.eazybytes.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EazyBankRouteConfiguration {

    /**
     * Add new custom microservices routes for RouteLocator.
     *
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator eazyBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                // add route which enable access to 'accounts' microservice via '/eazybank/accounts/' uri path
                .route(predicateSpec -> predicateSpec
                        .path("/eazybank/accounts/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/eazybank/accounts/(?<segment>,*)", "/${segment}")
                                .circuitBreaker(config -> config.setName("accountsCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport"))
                        )
                        .uri("lb://ACCOUNTS"))
                .route(predicateSpec -> predicateSpec
                        .path("/eazybank/cards/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/eazybank/cards/(?<segment>,*)", "/${segment}"))
                        .uri("lb://CARDS"))
                .route(predicateSpec -> predicateSpec
                        .path("/eazybank/loans/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/eazybank/loans/(?<segment>,*)", "/${segment}"))
                        .uri("lb://LOANS")).build();
    }
}
