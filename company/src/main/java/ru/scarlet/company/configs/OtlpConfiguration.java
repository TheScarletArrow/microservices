package ru.scarlet.company.configs;

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtlpConfiguration {

    OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url){
        return OtlpHttpSpanExporter.builder().setEndpoint(url).build();
    }
}