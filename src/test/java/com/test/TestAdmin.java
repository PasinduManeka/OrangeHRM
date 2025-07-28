package com.test;

import com.objects.AdminObject;
import com.objects.LoginObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

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
    private String UserName = "Admin";
    private String UserRole ="Admin";
    private String EmployeeName = "manda user";
    private String Status = "Disabled";

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

//    @DataProvider(name = "SearchData")
//    public Object[][] getSearchData(){
//        return new Object[][]{
//                {"Admin","Admin","Mand User", "Enabled"},
//                {"Admin","","", ""},
//                {"","Admin","", ""},
//                {"","","Mand User", ""},
//                {"","","", "Enabled"}
//        };
//    }

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
        admin.clickReset();
//        admin.setValueUserName(Username);
        Thread.sleep(1000);

        admin.clickSearch();
        Thread.sleep(10000);

        List<List<String>> tableData = admin.getAllTable();
        Thread.sleep(5000);

        Assert.assertTrue(!tableData.isEmpty(),"Expected results could not found");
    }

    @Test(priority = 4)
    public void testSearchFilterUserRole()throws InterruptedException{
        admin.clickAdminLable();
        Thread.sleep(500);
        admin.clickReset();
        admin.clickUserRole("ESS");
        Thread.sleep(500);
        admin.clickSearch();
        Thread.sleep(10000);

        List<List<String>> tableData = admin.getAllTable();
        Thread.sleep(5000);

        Assert.assertTrue(!tableData.isEmpty(),"Expected results could not found");

    }

    @Test(priority = 5)
    public void testSearchFilterStatus()throws InterruptedException{
        admin.clickAdminLable();
        Thread.sleep(1000);
        admin.clickReset();
        admin.clickStatus("Enabled");
        Thread.sleep(1000);

        admin.clickSearch();
        Thread.sleep(1000);

        List<List<String>> tableData = admin.getAllTable();
        Thread.sleep(10000);

        Assert.assertTrue(!tableData.isEmpty(),"No Values In Table.");
    }

    @Test(priority = 6)
    public void testSearchFilterEmployeeName()throws InterruptedException{
        admin.clickAdminLable();
        Thread.sleep(1000);
        admin.clickReset();
        admin.setValueEmployeeName("manda user");
        Thread.sleep(1000);

        admin.clickSearch();
        Thread.sleep(1000);

        List <List<String>> tableData = admin.getAllTable();
        Thread.sleep(1000);

        Assert.assertTrue(!tableData.isEmpty(),"No values in table.");
    }

    @Test(priority =7)
    public void testPositiveFilterResults()throws InterruptedException{
        admin.clickAdminLBL();
        Thread.sleep(10000);
        admin.clickReset();
//        admin.setValues(username,EmployeeName);
        admin.setValueUserName(UserName);
//        admin.setValueEmployeeName(EmployeeName);
        admin.clickUserRole(UserRole);
        admin.clickStatus(Status);
        Thread.sleep(1000);

        admin.clickSearch();
        Thread.sleep(100000);

        List<List<String>> tableData = admin.getAllTable();
        Thread.sleep(10000);

        Assert.assertTrue(!tableData.isEmpty(),"Expected results could not found");

//        System.out.println("Username:"+userName+"Role: "+role+"Employee Name"+employeeName+"Status: "+status);
    }

    @Test(priority = 8)
    public void testNegativeFilterResult()throws InterruptedException{
        admin.clickAdminLable();
        admin.clickReset();
        admin.setValueUserName(UserName);

        admin.clickUserRole(UserRole);
        admin.clickStatus("Enabled");
        Thread.sleep(1000);

        admin.clickSearch();
        Thread.sleep(10000);

        Assert.assertTrue(admin.notFoundMethaodVisible(),"Values are available");


    }


    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
