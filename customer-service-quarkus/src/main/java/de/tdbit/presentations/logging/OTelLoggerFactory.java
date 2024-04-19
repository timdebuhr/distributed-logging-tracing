package de.tdbit.presentations.logging;

import io.opentelemetry.api.OpenTelemetry;
import org.jboss.logmanager.LogContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.impl.Slf4jLogger;

public class OTelLoggerFactory implements ILoggerFactory {

    private final OpenTelemetry bridge;

    public OTelLoggerFactory(OpenTelemetry bridge) {
        this.bridge = bridge;
    }

    @Override
    public Logger getLogger(final String name) {
        io.opentelemetry.api.logs.Logger otelLogger = bridge.getLogsBridge().get(name);
        final org.jboss.logmanager.Logger lmLogger = LogContext.getLogContext().getLogger(name);
        final Logger jbossLogger = new Slf4jLogger(lmLogger);
        return new OTelLogger(name, jbossLogger, otelLogger);
    }
}
