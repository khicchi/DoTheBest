package net.kicchi.pages;

import net.kicchi.enums.EColumn;
import net.kicchi.functionality.TableFunctionality;
import net.kicchi.utils.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RushingTab implements TableFunctionality {
    public RushingTab(){
        PageFactory.initElements(DriverUtil.getDriver(), this);
    }

    int indexOfPlayer = 0;

    @FindBy(xpath = "//tbody//tr//td[3]")
    private List<WebElement> attValues;

    public String getPlayerName(){
        return DriverUtil.getDriver().findElement(By.xpath("//tbody/tr[" + indexOfPlayer + "]//a[@class='d3-o-player-fullname nfl-o-cta--link']")).getText();
    }

    @Override
    public int getMaxValueOfTheColumn(EColumn targetColumn) {
        int max = Integer.MIN_VALUE;
        int index = 0;

        List<WebElement> columnValues = null;
        if (targetColumn == EColumn.ATT)
            columnValues = attValues;

        for(WebElement webElement : columnValues)
        {
            index++;
            int currentYdsValue = Integer.parseInt(webElement.getText());
            if (currentYdsValue > max)
            {
                max = currentYdsValue;
                indexOfPlayer = index;
            }
        }

        return max;
    }

    @Override
    public int getMinValueOfTheColumn(EColumn targetColumn) {
        int min = Integer.MAX_VALUE;
        int index = 0;

        List<WebElement> columnValues = null;
        if (targetColumn == EColumn.ATT)
            columnValues = attValues;

        for(WebElement webElement : columnValues)
        {
            index++;
            int currentYdsValue = Integer.parseInt(webElement.getText());
            if (currentYdsValue < min)
            {
                min = currentYdsValue;
                indexOfPlayer = index;
            }
        }

        return min;
    }
}
