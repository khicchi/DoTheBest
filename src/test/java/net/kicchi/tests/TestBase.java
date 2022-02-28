package net.kicchi.tests;

import lombok.extern.log4j.Log4j2;
import net.kicchi.utils.BrowserUtil;
import net.kicchi.utils.DriverUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

@Log4j2
@ExtendWith(TestResultLoggerExtension.class)
public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUpTest(){
        driver = DriverUtil.getDriver();
        BrowserUtil.turnOnImplicitWaits();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDownTest(){
        DriverUtil.closeDriver();
    }

}
