package com.java.devops3.devops3;

import io.opentelemetry.exporter.zipkin.ZipkinExporter;
import io.opentelemetry.exporter.zipkin.ZipkinExporterBuilder;
import io.opentelemetry.sdk.autoconfigure.OpenTelemetrySdkAutoConfiguration;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SpanExporter;

@Configuration
@EnableConfigurationProperties(MyApplicationProperties.class)
public class OpenTelemetryConfiguration {
  @Autowired
  private MyApplicationProperties applicationProperties;

  @Bean
  public SpanExporter spanExporter() {
    ZipkinExporterBuilder builder = ZipkinExporter.builder()
      .setEndpoint(applicationProperties.getZipkinEndpoint())
      .setServiceName(applicationProperties.getZipkinServiceName());

    if (StringUtils.isNotEmpty(applicationProperties.getZipkinAccessToken())) {
      builder = builder.setAccessToken(applicationProperties.getZipkinAccessToken());
    }

    return builder.build();
  }

  @Bean
  public SdkTracerProvider tracerProvider(SpanExporter spanExporter) {
    return SdkTracerProvider.builder()
      .addSpanProcessor(SimpleSpanProcessor.builder(spanExporter).build())
      .build();
  }

  @Bean
  public Tracer tracer(SdkTracerProvider tracerProvider) {
    return tracerProvider.get(applicationProperties.getApplicationName());
  }
}
