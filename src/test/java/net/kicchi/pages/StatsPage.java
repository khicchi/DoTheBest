package net.kicchi.pages;

import lombok.Getter;
import net.kicchi.enums.EColumn;
import net.kicchi.functionality.TableFunctionality;
import net.kicchi.utils.BrowserUtil;
import net.kicchi.utils.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

@Getter
public class StatsPage extends BasePage{
    public StatsPage(){
        PageFactory.initElements(DriverUtil.getDriver(), this);
    }

    protected EColumn targetColumn;

    @FindBy(xpath = "//select[@class='d3-o-dropdown']")
    private WebElement yearDropDown;

    @FindBy(xpath = "//th[@class='header']/..//th[3]/a")
    private WebElement innerTabYds;


    public Select getYearSelectElement(){
        Select yearSelectElement = new Select(yearDropDown);
        return yearSelectElement;
    }

    public void selectTab(String tabName){
        By xpathLocatorTabName = By.xpath("//a[.='" + tabName + "']");
        BrowserUtil.waitForVisibility(xpathLocatorTabName, 10);
        BrowserUtil.waitForClickablility(xpathLocatorTabName, 10);
        DriverUtil.getDriver().findElement(xpathLocatorTabName).click();
    }
}
