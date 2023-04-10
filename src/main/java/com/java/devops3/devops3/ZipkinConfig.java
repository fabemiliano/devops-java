package com.java.devops3.devops3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;

@Configuration
public class ZipkinConfig {

//    @Bean
//    public Tracer tracer() {
//        // Cria um tracer
//        return OpenTelemetry.getTracerProvider().get("my-tracer");
//    }

    @Bean
    public Span span(Tracer tracer) {
        // Cria um span
        return tracer.spanBuilder("my-span").startSpan();
    }

    @Bean
    public ZipkinSpanExporter zipkinSpanExporter() {
        // Configura o endpoint do Zipkin
        String endpoint = "http://localhost:9411/api/v2/spans";

        // Cria um exporter para enviar spans para o Zipkin
        return ZipkinSpanExporter.builder()
                .setEndpoint(endpoint)
                .build();
    }

    @Bean
//    public SpanExporter spanExporter(ZipkinSpanExporter zipkinSpanExporter) {
//        // Cria um span exporter para enviar spans para o Zipkin
//        return SimpleSpanProcessor.builder(zipkinSpanExporter).build();
//    }
}
