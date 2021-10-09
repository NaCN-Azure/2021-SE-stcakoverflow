//package com.example.user.filter;
//
////import com.hpsyche.hpsychegatewayserver.utils.RedisUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class TokenFilter implements GlobalFilter {
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //token（用户身份）判断
//
//        ServerHttpRequest req = exchange.getRequest().mutate()
//                .header("from", "gateway").build();
//        return chain.filter(exchange.mutate().request(req.mutate().build()).build());
//    }
//}
