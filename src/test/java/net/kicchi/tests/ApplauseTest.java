package net.kicchi.tests;

import net.kicchi.enums.EColumn;
import net.kicchi.functionality.TableFunctionality;
import net.kicchi.pages.ReceivingTab;
import net.kicchi.pages.RushingTab;
import net.kicchi.pages.StatsPage;
import net.kicchi.utils.BrowserUtil;
import net.kicchi.utils.ConfigurationReaderUtil;
import net.kicchi.utils.DriverUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

public class ApplauseTest extends TestBase{

    /*
    Please use best practices and the page object model from Selenium to implement the following test steps:
- Navigate to https://www.nfl.com/
- Click on the "Stats" link from the page header.
- Please write a data-driven test case that gets the names of the players with the highest &
lowest value in the first page for:
Tab Name    |     Year    |   Required column value
---------------------------------------------------
Receiving         2020       Yds (Receiving Yards)
Rushing           2021       Att (Rushing Attempts)
Example Output:
---------------
Highest "<PLAYER_1>" : "<VALUE_1>"
Lowest  "<PLAYER_2>" : "<VALUE_2>"
     */

    private StatsPage statsPage;

    @ParameterizedTest
    @CsvSource({"Receiving,2020,Yds", "Rushing,2021,Att"})
    public void testTopAndMinimum(String statsTabName, String year, String columnName) throws InterruptedException {
        DriverUtil.getDriver().get(ConfigurationReaderUtil.getConfiguration().getMainPageUrl());
        statsPage = new StatsPage();
        EColumn targetColumn = Arrays.stream(EColumn.values()).filter(c -> c.getColumnName().equals(columnName)).findFirst().get();

        BrowserUtil.waitForVisibility(statsPage.getPopupLaterButton(), 10);

        statsPage.getPopupLaterButton().click();
        statsPage.getNavMenuStats().click();
        statsPage.selectTab(statsTabName);

        BrowserUtil.waitForPageToLoad(10);

        statsPage.getYearSelectElement().selectByVisibleText(year);

        BrowserUtil.waitForPageToLoad(10);

        TableFunctionality tableFunctionality = null;
        if (statsTabName.equals("Receiving"))
            tableFunctionality = new ReceivingTab();
        else if(statsTabName.equals("Rushing"))
            tableFunctionality = new RushingTab();

        int maxValue = tableFunctionality.getMaxValueOfTheColumn(targetColumn);
        System.out.println("Highest " + tableFunctionality.getPlayerName() + " : " + maxValue);

        int minValue = tableFunctionality.getMinValueOfTheColumn(targetColumn);
        System.out.println("Lowest " + tableFunctionality.getPlayerName() + " : " + minValue);
    }
}
