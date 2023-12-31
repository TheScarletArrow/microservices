package ru.scarlet.fileservice.configs

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class OtlpConfiguration {

    @Bean
    open fun otlpHttpSpanExporter(@Value("\${tracing.url}") url: String?): OtlpHttpSpanExporter {
        return OtlpHttpSpanExporter.builder().setEndpoint(url!!).build()
    }
}