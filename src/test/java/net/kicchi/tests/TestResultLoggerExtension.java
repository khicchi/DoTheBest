package net.kicchi.tests;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

@Log4j2
public class TestResultLoggerExtension implements TestWatcher {
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info(context.getTestMethod().get().getName() + " DISABLED");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info(context.getTestMethod().get().getName() + " SUCCESS");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info(context.getTestMethod().get().getName() + " ABORTED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        //TestWatcher.super.testFailed(context, cause);
        log.error(context.getTestMethod().get().getName() + " FAILED!", cause);
    }
}
