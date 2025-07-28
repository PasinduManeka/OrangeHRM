package com.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserObject extends AdminObject{
    WebDriver driver;
    WebDriverWait wait;

    //Elements
    By UserRole;
    By Status;

    public AddUserObject(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
        this.driver = driver;
        this.wait = wait;

        initElement();
    }

    public void initElement(){
        UserRole = By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]");
        Status = By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]");
    }

    //clicks
    public void clickAdminLabel(){
        clickAdminLBL();
    }
    public void clickAddButton(){
        clickAdd();
    }

    //select the dropdown values
    public void selectUserRole(String userrole)throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']//span[text()='"+userrole+"']"))).click();
    }

    public void selectStatus(String status)throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(Status)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']//span[text()='"+status+"']"))).click();;
    }

    //add form open
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
