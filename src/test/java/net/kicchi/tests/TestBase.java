package net.kicchi.tests;

import lombok.extern.log4j.Log4j2;
import net.kicchi.utils.BrowserUtil;
import net.kicchi.utils.DriverUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

@Log4j2
public class TestBase implements TestWatcher {

    protected WebDriver driver;

    @BeforeEach
    public void setUpTest(){
        driver = DriverUtil.getDriver();
        BrowserUtil.turnOnImplicitWaits();
        driver.manage().window().maximize();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        //TestWatcher.super.testSuccessful(context);
        log.info(context.getTestMethod().get().getName() + " SUCCESS");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        //TestWatcher.super.testFailed(context, cause);
        log.error(context.getTestMethod().get().getName() + " failed", cause);
    }

    @AfterEach
    public void tearDownTest(){

        DriverUtil.closeDriver();
    }

}
