package net.kicchi.pages;

import lombok.Getter;
import net.kicchi.utils.DriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class BasePage {
    public BasePage(){
        PageFactory.initElements(DriverUtil.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'Stats')]")
    private WebElement navMenuStats;

    @FindBy(xpath = "//button[.='Later']")
    private WebElement popupLaterButton;
}
