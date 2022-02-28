package net.kicchi.tests;

import net.kicchi.pages.ToDosPage;
import net.kicchi.utils.BrowserUtil;
import net.kicchi.utils.ConfigurationReaderUtil;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest extends TestBase{
    private ToDosPage toDosPage;

    @Test
    public void sampleTest(){

        driver.get(Objects.requireNonNull(ConfigurationReaderUtil.getConfiguration())
                .getMainPageUrl());
        toDosPage = new ToDosPage();

        BrowserUtil.turnOffImplicitWaits();
        assertThrows(NoSuchElementException.class,
                () -> System.out.println(toDosPage.getLeftItemCount()));
        assertThrows(NoSuchElementException.class,
                () -> System.out.println(toDosPage.getFilterElementActive().getText()));
        BrowserUtil.turnOnImplicitWaits();
    }
}
