package com.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminObject extends DashboardObject{

    WebDriver driver;
    WebDriverWait wait;

    By username;
    By userrole;
    By employeename;
    By status;
    By reset;
    By search;
    By add;
    By delete;
    By edit;
    
    public AdminObject(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
        this.driver = driver;
        this.wait = wait;

        initElements();
    }

    private void initElements(){
        username = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]");
        userrole = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/i[1]");
        employeename = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]");
        status = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[2]/i[1]");
        reset = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[1]");
        search = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[2]");
        add = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/button[1]");
        delete = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/button[1]");
        edit = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/button[2]");
    }

    //clicks
    public void clickAdminLBL(){
        clickAdminLable();
    }

    public void clickUserRole(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userrole)).click();
    }

    public void clickStatus(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(status)).click();
    }

    public void clickReset(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(reset)).click();
    }

    public void clickSearch(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(search)).click();
    }

    public void clickAdd(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(add)).click();
    }

    public void clickDelete(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(delete)).click();
    }

    public void clickEdit(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(edit)).click();
    }

    //setValues
    public void setValues(String userName, String employeeName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(userName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeename)).sendKeys(employeeName);
    }

    //elements avilabilty
    public boolean adminElementAvailable(){
        try{
            boolean userName = isElementAvailable(wait, username);
            boolean employeeName = isElementAvailable(wait,employeename);
            boolean userRole = isElementAvailable(wait,userrole);
            boolean Status = isElementAvailable(wait, status);
            boolean Reset = isElementAvailable(wait, reset);
            boolean Search = isElementAvailable(wait, search);
            boolean Add = isElementAvailable(wait, add);
            boolean Delete = isElementAvailable(wait, delete);
            boolean Edit = isElementAvailable(wait, edit);

            return userName && employeeName && userRole && Status && Reset && Search
                    && Add && Delete && Edit;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    private boolean isElementAvailable(WebDriverWait wait, By locator){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    //Add form
    public void addFormOpen(){
        boolean uiDirect = false;
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=expectedURL.equals(currentURL);
        }catch(Exception e){
            System.out.println("Error: "+e);
        }

    }



}
