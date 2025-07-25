package com.test;

import com.objects.DashboardObject;
import com.objects.LoginObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestDashboard {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    private String username = "Admin";
    private String password = "admin123";

    LoginObject login;
    DashboardObject dashboard;

    @BeforeClass
    public void setUp(){
        driver= new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        login =new LoginObject(driver,wait);
        dashboard = new DashboardObject(driver,wait);

    }

    @BeforeMethod
    public void loginToDashboard()throws InterruptedException{
        login.login(username,password);
    }

    @Test(priority = 1)
    public void checkElelementAvailable() throws InterruptedException{
        Thread.sleep(10000);
        Assert.assertTrue(dashboard.sidebarElementsAvailable(),"Elements are not available");
//        dashboard.clickOrders();
    }



    @AfterClass
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }




}
