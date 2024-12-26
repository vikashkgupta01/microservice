package com.example.api.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(0) //Ensure that filter runs first
public class LoggingGlobalFilter implements GlobalFilter {
    //private static final Logger logger = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //Log incoming requests
        String requestPath = exchange.getRequest().getURI().getPath();
        System.out.println("RequestPath ::"+requestPath);
       /*logger.info("Incoming request: Path = {}, Method = {}",
                requestPath,
                exchange.getRequest().getMethod());*/

        // Proceed with the filter chain and log response details
        return chain.filter(exchange).doFinally(signalType -> {
            //logger.info("Outgoing response for Path = {}, status = {}", requestPath, exchange.getResponse().getStatusCode());
        });
    }
}