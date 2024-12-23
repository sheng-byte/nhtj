package cn.edu.nhic.nhtj.filter;

import cn.edu.nhic.nhtj.common.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class AuthorizeFilter implements Ordered, GlobalFilter {
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if (request.getURI().getPath().contains("/auth")) {
            String token = request.getHeaders().getFirst("token");
            try {
                Integer adminid = JwtUtils.verifyAdminTokenAndGetId(token);
            } catch (Exception e) {
                e.printStackTrace();
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", 401);
                map.put("msg", "token无效");
                String json = new ObjectMapper().writeValueAsString(map);
                DataBuffer buffer = response.bufferFactory().wrap(json.getBytes());
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

                return response.writeWith(Mono.just(buffer));
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
