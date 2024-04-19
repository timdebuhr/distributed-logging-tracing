package de.tdbit.presentations.logging;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.ResourceAttributes;
import jakarta.inject.Singleton;
import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.impl.Slf4jMDCAdapter;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

@Singleton
public class OTelSlf4jServiceProvider implements SLF4JServiceProvider {

    private OpenTelemetry openTelemetry;
    private OTelLoggerFactory loggerFactory;
    private final IMarkerFactory markerFactory;
    private final MDCAdapter mdcAdapter;

    public OTelSlf4jServiceProvider() {
        this.markerFactory = new BasicMarkerFactory();
        this.mdcAdapter = new Slf4jMDCAdapter();
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return markerFactory;
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return mdcAdapter;
    }

    @Override
    public String getRequestedApiVersion() {
        return "2.0.6";
    }

    @Override
    public void initialize() {
        if (openTelemetry == null) {
            openTelemetry = initTelemetry();
        }
        loggerFactory = new OTelLoggerFactory(openTelemetry);
    }

    private OpenTelemetry initTelemetry() {
        Resource resource = Resource.getDefault()
                .toBuilder()
                .put(ResourceAttributes.SERVICE_NAME, "dlt_customer_service")
                .put(ResourceAttributes.SERVICE_VERSION, "1.0.0-SNAPSHOT")
                .build();

        SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
                .registerMetricReader(PeriodicMetricReader.builder(OtlpGrpcMetricExporter.builder().build()).build())
                .setResource(resource)
                .build();

        SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder()
                .addLogRecordProcessor(
                        BatchLogRecordProcessor.builder(OtlpGrpcLogRecordExporter.builder().build()).build())
                .setResource(resource)
                .build();

        return OpenTelemetrySdk.builder()
                .setMeterProvider(sdkMeterProvider)
                .setLoggerProvider(sdkLoggerProvider)
                .setPropagators(ContextPropagators.create(TextMapPropagator
                        .composite(W3CTraceContextPropagator.getInstance(), W3CBaggagePropagator.getInstance())))
                .build();
    }
}
