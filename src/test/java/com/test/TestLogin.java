package com.test;

import com.objects.LoginObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;

public class TestLogin {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private String username = "Admin";
    private String password = "admin123";
    private String nullUsername = "";
    private String nullPassword = "";

    @BeforeTest
    public void setup(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void checkElelementAvailable() throws InterruptedException{
        LoginObject login = new LoginObject(driver, wait);
        driver.get(URL);
        Thread.sleep(20000);

        Assert.assertTrue(login.loginElementsAvailable(),"elements are not available");
    }

    @Test(priority = 5)
    public void successfulLoginFunction() throws InterruptedException{
        LoginObject login = new LoginObject(driver,wait);
        driver.get(URL);

        Thread.sleep(10000);

        login.setValues(username,password);
        login.setSubmit();

        Thread.sleep(20000);

        Assert.assertTrue(login.verifySuccessLogin(),"login failed");

    }

   @Test(priority = 2)
   public void checkEmpltyValidation() throws InterruptedException{
        LoginObject login = new LoginObject(driver,wait);

        driver.get(URL);
        login.setSubmit();

        Thread.sleep(10000);

        Assert.assertTrue(login.emptyValidation(),"Empty validations are not displayed.");
        Thread.sleep(500);
   }

   @Test(priority = 3)
   public void  checkUsernameNullValidation() throws InterruptedException{
       LoginObject login = new LoginObject(driver,wait);

       driver.get(URL);
       login.setValues(nullUsername,password);
       login.setSubmit();

       Thread.sleep(10000);

       Assert.assertTrue(login.captureOneEmptyValidation(),"Empty validations are not displayed.");
       Thread.sleep(500);
   }

    @Test(priority = 4)
    public void  checkPasswordNullValidation() throws InterruptedException{
        LoginObject login = new LoginObject(driver,wait);

        driver.get(URL);
        login.setValues(username,nullPassword);
        login.setSubmit();

        Thread.sleep(10000);

        Assert.assertTrue(login.captureOneEmptyValidation(),"Empty validations are not displayed.");
        Thread.sleep(500);
    }

    @AfterClass
    public void TearDown(){
        if(driver!=null){
            driver.quit();;
        }
    }


}
