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
import java.util.List;

public class TestAdmin {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private String url = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";

    //login
    private String username = "Admin";
    private String password = "admin123";

    //filter
    private String Username = "Admin";
    private String UserRole ="Admin";
    private String EmployeeName = "Sobor Ali";
    private String Staus = "Disabled";

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
    public void testDirectPage()throws InterruptedException{
        admin.clickAdminLBL();
        Thread.sleep(10000);
        Assert.assertTrue(admin.adminUserOpen(),"Direct to the wrong page.");
    }

    @Test(priority = 2)
    public void checkAdminElelementAvailable()throws InterruptedException{
        admin.clickAdminLable();
        Thread.sleep(10000);
        Assert.assertTrue(admin.adminElementAvailable(),"Elements are not available");
    }

    //Search
    @Test(priority = 3)
    public void testSearchFilterUsrName()throws InterruptedException{
        admin.clickAdminLable();
        Thread.sleep(1000);
        admin.setValueUserName(Username);
        Thread.sleep(1000);

        admin.clickSearch();
        Thread.sleep(10000);

        List<List<String>> tableData = admin.getAllTable();
        Thread.sleep(5000);

        Assert.assertTrue(!tableData.isEmpty(),"Expected results could not found");
    }

    @Test(priority =4)
    public void testPositiveFilterResults() throws InterruptedException{
        admin.clickAdminLBL();
        Thread.sleep(10000);
        admin.setValues(Username,EmployeeName);
        admin.clickUserRole();
        admin.clickStatus();
        Thread.sleep(1000);

        admin.clickSearch();
        Thread.sleep(100000);

        List<List<String>> tableData = admin.getAllTable();
        Thread.sleep(10000);

        Assert.assertTrue(!tableData.isEmpty(),"Expected results could not found");
    }


    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
