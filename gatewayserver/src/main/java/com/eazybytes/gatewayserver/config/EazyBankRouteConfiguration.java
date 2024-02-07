package com.eazybytes.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;

@Configuration
public class EazyBankRouteConfiguration {

    private final RedisRateLimiterConfiguration redisRateLimiterConfiguration;

    public EazyBankRouteConfiguration(RedisRateLimiterConfiguration redisRateLimiterConfiguration) {
        this.redisRateLimiterConfiguration = redisRateLimiterConfiguration;
    }

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
                        .uri("http://accounts:8080"))
                .route(predicateSpec -> predicateSpec
                        .path("/eazybank/cards/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/eazybank/cards/(?<segment>,*)", "/${segment}")
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
                        )
                        .uri("http://cards:9000"))
                .route(predicateSpec -> predicateSpec
                        .path("/eazybank/loans/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/eazybank/loans/(?<segment>,*)", "/${segment}")
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiterConfiguration.redisRateLimiter())
                                        .setKeyResolver(redisRateLimiterConfiguration.userKeyResolver()))
                        )
                        .uri("http://loans:8090")).build();
    }
}
