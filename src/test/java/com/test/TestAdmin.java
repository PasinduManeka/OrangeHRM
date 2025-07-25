package com.test;

import com.objects.AdminObject;
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

public class TestAdmin {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private String url = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";

    //login
    private String username = "Admin";
    private String password = "admin123";

    LoginObject login;
    AdminObject admin;

    @BeforeClass
    public void setUp(){
        driver=new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        login = new LoginObject(driver, wait);
        admin = new AdminObject(driver,wait);
    }

    @BeforeMethod
    public void loginToAdmin()throws InterruptedException{
        login.login(username,password);
    }

    @Test(priority = 1)
    public void checkAdminElelementAvailable()throws InterruptedException{
        admin.clickAdminLable();
        Thread.sleep(10000);
        Assert.assertTrue(admin.adminElementAvailable(),"Elements are not available");
    }

    //Search
    @Test(priority =2)
    public void testPositiveFilterResults(){
        admin.setValues();
    }


    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
