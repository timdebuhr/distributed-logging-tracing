import { registerInstrumentations } from '@opentelemetry/instrumentation';
import { getWebAutoInstrumentations } from '@opentelemetry/auto-instrumentations-web';
import { BatchSpanProcessor, ConsoleSpanExporter, SimpleSpanProcessor, WebTracerProvider } from '@opentelemetry/sdk-trace-web';
import { ZoneContextManager } from '@opentelemetry/context-zone';
import { SemanticResourceAttributes } from '@opentelemetry/semantic-conventions';
import { Resource } from '@opentelemetry/resources';
import { OTLPTraceExporter } from '@opentelemetry/exporter-trace-otlp-proto';

const collectorOptions = {
    url: 'http://localhost:4318/v1/traces',
};
const providerConfig = {
    resource: new Resource({
        [SemanticResourceAttributes.SERVICE_NAME]: 'wjax_frontend_one',
    }),
};
const provider = new WebTracerProvider(providerConfig);
provider.addSpanProcessor(new SimpleSpanProcessor(new ConsoleSpanExporter()));
provider.addSpanProcessor(new BatchSpanProcessor(new OTLPTraceExporter(collectorOptions)));
provider.register({
    contextManager: new ZoneContextManager(),
});

registerInstrumentations({
    instrumentations: [getWebAutoInstrumentations()],
});

export function TraceProvider ({ children }) {
    return (<>{children}</>);
}
