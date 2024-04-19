package de.tdbit.presentations.logging;

import io.opentelemetry.api.logs.Severity;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.MessageFormatter;

import java.util.Arrays;

public class OTelLogger implements Logger {

    private final String name;
    private final Logger jbossLogger;
    private final io.opentelemetry.api.logs.Logger otelLogger;

    public OTelLogger(String name,
                      Logger jbossLogger,
                      io.opentelemetry.api.logs.Logger otelLogger) {
        this.name = name;
        this.jbossLogger = jbossLogger;
        this.otelLogger = otelLogger;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isTraceEnabled() {
        return jbossLogger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        doLog(msg, Level.TRACE);
    }

    @Override
    public void trace(String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(msg, Level.TRACE);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(msg, Level.TRACE);
    }

    @Override
    public void trace(String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(msg, Level.TRACE);
    }

    @Override
    public void trace(String msg, Throwable t) {
        doLog(msg, t, Level.TRACE);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return jbossLogger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        doLog(marker, msg, Level.TRACE);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(marker, msg, Level.TRACE);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(marker, msg, Level.TRACE);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        String msg = MessageFormatter.arrayFormat(format, argArray).getMessage();
        doLog(marker, msg, Level.TRACE);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        doLog(marker, msg, Level.TRACE);
    }

    @Override
    public boolean isDebugEnabled() {
        return jbossLogger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        doLog(msg, Level.DEBUG);
    }

    @Override
    public void debug(String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(msg, Level.DEBUG);

    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(msg, Level.DEBUG);
    }

    @Override
    public void debug(String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(msg, Level.DEBUG);
    }

    @Override
    public void debug(String msg, Throwable t) {
        doLog(msg, Level.DEBUG);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return jbossLogger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        doLog(marker, msg, Level.DEBUG);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(marker, msg, Level.DEBUG);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(marker, msg, Level.DEBUG);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(marker, msg, Level.DEBUG);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        doLog(marker, msg, t, Level.DEBUG);
    }

    @Override
    public boolean isInfoEnabled() {
        return jbossLogger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        doLog(msg, Level.INFO);
    }

    @Override
    public void info(String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(msg, Level.INFO);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(msg, Level.INFO);
    }

    @Override
    public void info(String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(msg, Level.INFO);
    }

    @Override
    public void info(String msg, Throwable t) {
        doLog(msg, t, Level.INFO);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return jbossLogger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg) {
        doLog(marker, msg, Level.INFO);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(marker, msg, Level.INFO);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(marker, msg, Level.INFO);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(marker, msg, Level.INFO);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        doLog(marker, msg, t, Level.INFO);
    }

    @Override
    public boolean isWarnEnabled() {
        return jbossLogger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        doLog(msg, Level.WARN);
    }

    @Override
    public void warn(String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(msg, Level.WARN);
    }

    @Override
    public void warn(String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(msg, Level.WARN);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(msg, Level.WARN);
    }

    @Override
    public void warn(String msg, Throwable t) {
        doLog(msg, t, Level.WARN);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return jbossLogger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg) {
        doLog(marker, msg, Level.WARN);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(marker, msg, Level.WARN);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(marker, msg, Level.WARN);
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(marker, msg, Level.WARN);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        doLog(marker, msg, t, Level.WARN);
    }

    @Override
    public boolean isErrorEnabled() {
        return jbossLogger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        doLog(msg, Level.ERROR);
    }

    @Override
    public void error(String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(msg, Level.ERROR);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(msg, Level.ERROR);
    }

    @Override
    public void error(String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(msg, Level.ERROR);
    }

    @Override
    public void error(String msg, Throwable t) {
        doLog(msg, t, Level.ERROR);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return jbossLogger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        doLog(marker, msg, Level.ERROR);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        String msg = MessageFormatter.format(format, arg).getMessage();
        doLog(marker, msg, Level.ERROR);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        String msg = MessageFormatter.format(format, arg1, arg2).getMessage();
        doLog(marker, msg, Level.ERROR);
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        String msg = MessageFormatter.arrayFormat(format, arguments).getMessage();
        doLog(marker, msg, Level.ERROR);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        doLog(marker, msg, t, Level.ERROR);
    }

    private void doLog(Marker marker, String msg, Level level) {
        jbossLogger.atLevel(level).addMarker(marker).log(msg);
        otelLogger.logRecordBuilder()
                .setSeverity(Severity.valueOf(level.name()))
                .setBody(msg)
                .emit();
    }

    private void doLog(Marker marker, String msg, Throwable throwable, Level level) {
        jbossLogger.atLevel(level).addMarker(marker).setCause(throwable).log(msg);
        otelLogger.logRecordBuilder()
                .setSeverity(Severity.valueOf(level.name()))
                .setBody(msg + "\n" + Arrays.toString(throwable.getStackTrace()))
                .emit();
    }

    private void doLog(String msg, Level level) {
        jbossLogger.atLevel(level).log(msg);
        otelLogger.logRecordBuilder()
                .setSeverity(Severity.valueOf(level.name()))
                .setBody(msg)
                .emit();
    }

    private void doLog(String msg, Throwable throwable, Level level) {
        jbossLogger.atLevel(level).setCause(throwable).log(msg);
        otelLogger.logRecordBuilder()
                .setSeverity(Severity.valueOf(level.name()))
                .setBody(msg + "\n" + Arrays.toString(throwable.getStackTrace()))
                .emit();
    }
}
