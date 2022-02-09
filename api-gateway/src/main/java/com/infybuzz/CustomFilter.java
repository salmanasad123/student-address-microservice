package com.infybuzz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        logger.info("Authorization = " + serverHttpRequest.getHeaders().getFirst("Authorization"));

        // pass the request further
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // post filter
                    ServerHttpResponse serverHttpResponse = exchange.getResponse();
                    logger.info("Response code " + serverHttpResponse.getStatusCode());
                }));
    }
}
