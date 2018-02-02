package com.nexant.dsm.dashboard;

import com.nexant.common.Locator;
import com.nexant.common.SeleniumUtils;
import com.nexant.webdriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Created by tshakirov on 5/24/2015.
 */
public class TestDashboard {

    private static WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = Driver.get();
    }

    @Test
    public void navigate(){
        SeleniumUtils.goToTab(driver, Locator.L_TAB_DASHBOARD, "DSM Central: Dashboard");
     //   SeleniumUtils.sleepMillis(3000);
    }

    @Test
    public void testGas(){
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_GAS);
        SeleniumUtils.click(driver, Locator.Dashboard.DB_GAS);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("Programs : Gas"));
  //      SeleniumUtils.sleepMillis(4000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_YTD_BUDGET);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_YTD_BUDGET);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_GAS_THERM);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_GAS_THERM);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);
        
        SeleniumUtils.sleepMillis(2000);
        
        //Check the units of the chart
		 By by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][3]/*[name()='g'][@class='fusioncharts-yaxis-0-gridlabels']/*[1]/*");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "MDth"); 
		 SeleniumUtils.sleepMillis(2000);
        
        //Check the legend on the graph
		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[5]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "Therm Savings"); 
		 SeleniumUtils.sleepMillis(2000);

		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[9]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "Therm Target"); 
		 SeleniumUtils.sleepMillis(2000);
		 
		 
        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

    }

    @Test
    public void testWater(){
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_WATER);
        SeleniumUtils.click(driver, Locator.Dashboard.DB_WATER);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("Programs : Water"));
   //     SeleniumUtils.sleepMillis(4000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_YTD_BUDGET);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_YTD_BUDGET);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_WATER_THERM);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_WATER_THERM);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);

        SeleniumUtils.sleepMillis(2000);
        
        //Check the units of the chart
		 By by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][3]/*[name()='g'][@class='fusioncharts-yaxis-0-gridlabels']/*[1]/*");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "kGal"); 
		 SeleniumUtils.sleepMillis(2000);
       
		 //Check the legend on the graph
		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[5]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "Gal Savings"); 
		 SeleniumUtils.sleepMillis(2000);

		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[9]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "Gal Target"); 
		 SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

    }

    @Test
    public void testRenewable(){
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_RENEWABLE);
        SeleniumUtils.click(driver, Locator.Dashboard.DB_RENEWABLE);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("Programs : Renewable"));
  //      SeleniumUtils.sleepMillis(4000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_YTD_BUDGET);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_YTD_BUDGET);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_RENEWABLE_THERM);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_RENEWABLE_THERM);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);

        SeleniumUtils.sleepMillis(2000);
        
        //Check the units of the chart
		 By by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][3]/*[name()='g'][@class='fusioncharts-yaxis-0-gridlabels']/*[1]/*");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "MW"); 
		 SeleniumUtils.sleepMillis(2000);
       
		//Check the legend on the graph
		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[5]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "kW Installed"); 
		 SeleniumUtils.sleepMillis(2000);

		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[9]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "kW Target"); 
		 SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);


    }

    @Test
    public void testElectric(){
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_ELECTRIC);
        SeleniumUtils.click(driver, Locator.Dashboard.DB_ELECTRIC);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.divTextContains("Programs : Electric"));
   //     SeleniumUtils.sleepMillis(4000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_SEC);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SPENDBY_CAT);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_YTD_BUDGET);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_YTD_BUDGET);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_ELECTRIC_KWH);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_ELECTRIC_KWH);

        SeleniumUtils.sleepMillis(2000);

        SeleniumUtils.clickonfusionel(driver);

        SeleniumUtils.sleepMillis(2000);
        
        //Check the units of the chart
		 By by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][3]/*[name()='g'][@class='fusioncharts-yaxis-0-gridlabels']/*[1]/*");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "MWh"); 
		 SeleniumUtils.sleepMillis(2000);
       
		//Check the legend on the graph
		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[5]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "kWh Savings"); 
		 SeleniumUtils.sleepMillis(2000);

		 by =By.xpath("//div[@id='lineChartGlobalId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[9]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "kWh Target"); 
		 SeleniumUtils.sleepMillis(2000);


        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_CLOSE_POP_UP);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_CLOSE_POP_UP);

        SeleniumUtils.clickonfusionel(driver, By.xpath("//div[@id='lineChartGlobalId_chart']"));

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_DEMAND_GRAPH);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_DEMAND_GRAPH);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_ENERGY_GRAPH);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_ENERGY_GRAPH);
        
        SeleniumUtils.sleepMillis(3000);
		 
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SORT_MAX);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SORT_MAX);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SORT_1Y);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SORT_1Y);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SORT_2Y);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SORT_2Y);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SORT_3Y);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SORT_3Y);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SORT_YTD);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SORT_YTD);

        SeleniumUtils.sleepMillis(3000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SHOW_10);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SHOW_10);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SHOW_25);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SHOW_25);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_SHOW_5);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_SHOW_5);

        SeleniumUtils.sleepMillis(3000);

        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_TRANSPORTATION_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_TRANSPORTATION_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_ST_PUBLIC_AUTHORITY_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_ST_PUBLIC_AUTHORITY_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_RESIDENTIAL_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_RESIDENTIAL_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_PUBLIC_AUTHORITY_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_PUBLIC_AUTHORITY_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_NON_RESIDENTIAL_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_NON_RESIDENTIAL_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_MINING_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_MINING_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_Irrigation_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_Irrigation_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_Industrial_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_Industrial_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_Commercial_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_Commercial_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_Agriculture_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_Agriculture_TAB);
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_ALL_TAB);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_ALL_TAB);
        

        SeleniumUtils.sleepMillis(3000);
        
        SeleniumUtils.assertElementExistsWithWait(driver, Locator.Dashboard.DB_DEMAND_GRAPH);
        SeleniumUtils.clickUsingJavascript(driver, Locator.Dashboard.DB_DEMAND_GRAPH);
        SeleniumUtils.sleepMillis(3000);
        
        SeleniumUtils.clickonfusionel(driver, By.xpath("//div[@id='portfolioDemandId_chart']"));

        //Check the units of the chart
		 by =By.xpath("//div[@id='portfolioDemandId_chart']/span/*[name()='svg']/*[name()='g'][3]/*[name()='g'][@class='fusioncharts-yaxis-0-gridlabels']/*[1]/*");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "MW"); 
		 SeleniumUtils.sleepMillis(2000);
     
		//Check the legend on the graph
		 by =By.xpath("//div[@id='portfolioDemandId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[5]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "kW Savings"); 
		 SeleniumUtils.sleepMillis(2000);

		 by =By.xpath("//div[@id='portfolioDemandId_chart']/span/*[name()='svg']/*[name()='g'][@class='fusioncharts-legend']/*[9]/*[1]");
		 SeleniumUtils.assertElementInnerHtmlContainsWithWait(driver, by, "kW Target"); 
		 SeleniumUtils.sleepMillis(2000);
       
    }

}
