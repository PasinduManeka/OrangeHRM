package com.test;

import com.objects.AddUserObject;
import com.objects.LoginObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAddUser {
    private static WebDriver driver;
    private static WebDriverWait wait;

    LoginObject loginObject;
    AddUserObject adduser;

    //login data
    private String username = "Admin";
    private String password = "admin123";

    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        loginObject = new LoginObject(driver,wait);
        adduser = new AddUserObject(driver,wait);

    }

    @BeforeMethod
    public void loginToAdmin()throws InterruptedException{
        loginObject.login(username,password);
    }

    @Test(priority=1)
    public void testAddUserSuccessful()throws InterruptedException{
        adduser.clickAdminLable();
        adduser.clickAdd();


        adduser.selectUserRole("Admin");
        adduser.selectStatus("Enabled");
        Thread.sleep(1000);

    }

}
