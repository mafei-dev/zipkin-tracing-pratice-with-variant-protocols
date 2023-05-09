package com.example.service6;

import io.micrometer.context.ContextSnapshot;
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@AllArgsConstructor
//@Slf4j
public class Service6Application {

    @Bean
    public WebClient.Builder builder() {
        return WebClient.builder();
    }

    private static final Logger log = LoggerFactory.getLogger(Service6Application.class);
    private final Tracer tracer;

    public static void main(String[] args) {
        SpringApplication.run(Service6Application.class, args);
        Hooks.enableAutomaticContextPropagation();
        Hooks.enableContextLossTracking();

    }

    @GetMapping("/test")
    public Mono<ResponseEntity<Map<String, String>>> index(
            ServerWebExchange request
    ) {
        request.getRequest().getHeaders().forEach((s, strings) -> {
            System.out.println("key " + s + ",value : " + strings);
        });

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("data", "test");
        return Mono.just(ResponseEntity.ok(stringMap));
        /*return Mono.deferContextual(contextView -> {
            String _parentId = request.getRequest().getHeaders().getFirst("X-B3-ParentSpanId");
            System.out.println("_parentId = " + _parentId);
            try (ContextSnapshot.Scope scope = ContextSnapshot.setThreadLocalsFrom(contextView,
                    ObservationThreadLocalAccessor.KEY)) {
                *//*String traceId = this.tracer.currentSpan().context().traceId();
                TraceContext build = this.tracer.traceContextBuilder()
                        .parentId(_parentId)
                        .spanId(this.tracer.currentTraceContext().context().spanId())
                        .traceId(this.tracer.currentTraceContext().context().traceId())
                        .build();
                this.tracer.spanBuilder().setParent(build).start();
*//*
//                MDC.put("parentId", _parentId1);
                String parentId = this.tracer.currentTraceContext().context().parentId();
                System.out.println("parentId>" + parentId);
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("data", "test");
                return Mono.just(ResponseEntity.ok(stringMap));
            }
        });*/
    }
}
