package com.test;

import com.objects.AddUserObject;
import com.objects.LoginObject;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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

    public Object[][] addFormDat(){
        return new Object[][]{
                {"","","","", "", "", "empty"},  // empty fields
                {"Admin","Enabled","Orange Test", "Orange", "Test@123", "Test@123", "valid"},
                {"Admin","Enabled","Orange Test", "Orange", "Test@123", "Test@123", "alreadyTaken"},  // empty fields

        };
    }

    @Test(priority=1)
    public void testSuccessPageDirect()throws InterruptedException{
        adduser.clickAdminLable();
        adduser.clickAdd();
        Thread.sleep(1000);

        Assert.assertTrue(adduser.addFormOpen(), "Form is not open.");

    }

    @Test(priority = 2)
    public void testAdddUserElementAvailable()throws  InterruptedException{
        adduser.clickAdminLable();
        Thread.sleep(500);
        adduser.clickAdd();
        Thread.sleep(10000);
        Assert.assertTrue(adduser.addFormElementAvailable(),"Elements are not available.");
    }

    @Test(priority =3)
    public void testEmptyForm()throws InterruptedException{
        adduser.clickAdminLBL();
        adduser.clickAdd();
        Thread.sleep(1000);

        adduser.clickSaveButton();
        Thread.sleep(10000);

        Assert.assertTrue(adduser.emptyValidation(),"Empty validation error messages does not display.");


    }

    //already taken
    @Test(priority = 3)
    public void alreadyTaken()throws InterruptedException{
        adduser.clickAdminLabel();
        adduser.clickAdd();
        Thread.sleep(1000);

        adduser.setValuesAddForm("Admin","test@123", "test@123");
        adduser.selectUserRole("Admin");
        adduser.selectEmployeeName("Orange", "Orange Test");
        adduser.selectStatus("Enabled");

        adduser.clickSaveButton();
        Thread.sleep(10000);

        Assert.assertTrue(adduser.alreadyTakenUserName(),"AlreadyTaken validation is not working");

    }

    @Test(priority=5)
    public void testAddUserSuccessful(String userRole,String Status, String EmpName, String Username, String Password, String ConfPass, String scenario )throws InterruptedException{
        adduser.clickAdminLable();
        adduser.clickAdd();
        Thread.sleep(1000);

//        adduser.setValuesAddForm("Orange Test", "Orange", "Test@123", "Test@123");
        adduser.setValuesAddForm(Username, Password, ConfPass);
//        adduser.setValueAddForm("Test1");
        Thread.sleep(1000);
        adduser.selectUserRole(userRole);
        Thread.sleep(1000);
        adduser.selectStatus(Status);
        Thread.sleep(1000);
//        adduser.clickSaveButton();

        switch(scenario){
            case "empty":
                Assert.assertTrue(adduser.emptyValidation(),"Empty validation error messages does not display.");
                break;

            case "alreadyTaken":
        }

    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }



}
