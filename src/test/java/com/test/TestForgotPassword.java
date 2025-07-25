package com.test;

import com.objects.ForgotPasswordObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class TestForgotPassword {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private String loginURL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private String forgotPassword = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
    private String username = "Admin";
    private String nullusername = "";

    @BeforeTest
    public void setUp() {
//		FirefoxOptions options = new FirefoxOptions();

        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));


    }

    @Test(priority=1)
    public void successfullydirectForgotPssword() throws InterruptedException{
        ForgotPasswordObject forgot = new ForgotPasswordObject(driver,wait);
        driver.get(loginURL);
        Thread.sleep(10000);

        forgot.clickForgotPassword();

        Thread.sleep(1000);

        Assert.assertTrue(forgot.isOpenForgotPassword(), "Page did not direct.");


    }

    @Test(priority=2)
    public void checkElementAvailable() throws InterruptedException{
        ForgotPasswordObject forgot = new ForgotPasswordObject(driver,wait);

        driver.get(forgotPassword);
        Thread.sleep(10000);

        Assert.assertTrue(forgot.elementsPresent(),"Elements are not displayed.");

    }

    @Test(priority=3)
    public void verifyAllStaticTexts() throws InterruptedException{
        ForgotPasswordObject forgot = new ForgotPasswordObject(driver,wait);

        driver.get(forgotPassword);

        Thread.sleep(30000);

        Map<String, String> uiTexts = forgot.getStaticTexts();

//		System.out.println(uiTexts);

        Assert.assertEquals(uiTexts.get("heading"),"Reset Password");
        Assert.assertEquals(uiTexts.get("username"),"Username");
        Assert.assertEquals(uiTexts.get("cancel"),"Cancel");
        Assert.assertEquals(uiTexts.get("Reset"),"Reset Password");

    }

    @Test(priority=4)
    public void verifyCancelfunction() throws InterruptedException{
        driver.get(forgotPassword);

        ForgotPasswordObject forgot = new ForgotPasswordObject(driver,wait);

        Thread.sleep(1000);

        forgot.clickCancel();

        Thread.sleep(5000);

        Assert.assertTrue(forgot.isOpenLogin(),"Page not directed.");

//		System.out.println( forgot.isOpenLogin());
//
//		Thread.sleep(2000);

    }

    @Test(priority=5)
    public void verifyReqValidationDisplay() throws InterruptedException{
        driver.get(forgotPassword);

        ForgotPasswordObject forgot = new ForgotPasswordObject(driver,wait);

        forgot.setValues(nullusername);

        Thread.sleep(1000);

        forgot.clickReset();

        Thread.sleep(1000);

        Assert.assertTrue(forgot.isEmptyField(),"No required field display.");

    }

    @Test(priority=6)
    public void verifyFormSuccessfulySubmitted() throws InterruptedException{
        driver.get(forgotPassword);

        ForgotPasswordObject forgot = new ForgotPasswordObject(driver,wait);

        forgot.setValues(username);

        Thread.sleep(2000);

        forgot.clickReset();

        Thread.sleep(1000);

        Assert.assertTrue(forgot.isSuccessfulPasswordReset(),"Password has not rest.");

    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }


}
