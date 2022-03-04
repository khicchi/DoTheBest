package net.kicchi.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * My precious browser utilities
 * growing day by day
 */
public class BrowserUtil {
    /**
     * return a list of string that contains the text (retrieved by getText method)
     * from the input list of elements
     * @param list of WebElements to fetch their texts
     * @return list of string
     */
    public static List<String> getElementsText(List<WebElement> list) {
        if (list == null)
            return null;

        return list.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * waits for backgrounds processes on the browser to complete
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Waits for element to be not stale
     * @param element
     */
    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    /**
     * Clicks on an element using JavaScript
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) DriverUtil.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) DriverUtil.getDriver())
                .executeScript("arguments[0].click();", element);
    }


    /**
     * Scrolls down to an element using JavaScript
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) DriverUtil.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Performs double click action on an element
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(DriverUtil.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     * @param element
     * @param attributeName
     * @param attributeValue
     */
    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) DriverUtil.getDriver())
                .executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                        element, attributeName, attributeValue);
    }

    /**
     * Highlighs an element by changing its background and border color
     * @param element
     */
    public static void highlight(WebElement element) {
        try {
            ((JavascriptExecutor) DriverUtil.getDriver())
                    .executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                            element);
            Thread.sleep(1000);
            ((JavascriptExecutor) DriverUtil.getDriver()).
                    executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void turnOffImplicitWaits() {
        DriverUtil.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    public static void turnOnImplicitWaits() {
        DriverUtil.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    public static void scrollToEndOfThePage(){
        JavascriptExecutor js = ((JavascriptExecutor) DriverUtil.getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static String getScreenshot(String name) throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) DriverUtil.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    /**
     * Switches to new window by the exact title. Returns to original window if target title not found
     * @param targetTitle
     */
    public static void switchToWindow(String targetTitle,WebDriver driver) {
        String origin = DriverUtil.getDriver().getWindowHandle();
        for (String handle : DriverUtil.getDriver().getWindowHandles()) {
            DriverUtil.getDriver().switchTo().window(handle);
            if (DriverUtil.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        DriverUtil.getDriver().switchTo().window(origin);
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(DriverUtil.getDriver());
        actions.moveToElement(element).perform();
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }

    public static void clickWithWait(By by, int attempts) {
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while (counter < attempts) {
            try {
                //selenium must look for element again
                clickWithJS(DriverUtil.getDriver().findElement(by));
                //if click is successful - then break
                break;
            } catch (WebDriverException e) {
                //if click failed
                //print exception
                //print attempt
                e.printStackTrace();
                ++counter;
                //wait for 1 second, and try to click again
                waitFor(1);
            }
        }
    }

    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverUtil.getDriver();
        jse.executeScript(command, element);
    }

    public static void waitForPresenceOfElement(By by, long timeToWaitInSeconds) {
        new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(timeToWaitInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
