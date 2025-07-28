package com.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserObject extends AdminObject{
    private static WebDriver driver;
    private static WebDriverWait wait;

    //Elements
    By UserRole;
    By Status;
    By employeeName;
    By userName;
    By password;
    By confirmPassword;
    By cancelButton;
    By saveButton;
    By errorReq;

    public AddUserObject(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
        this.driver = driver;
        this.wait = wait;

        initElement();
    }

    public void initElement(){
        UserRole = By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]");
        Status = By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]");
        employeeName = By.xpath("//input[@placeholder='Type for hints...']");
        userName = By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-2 orangehrm-full-width-grid']/div[4]/div[1]/div[2]");
        password = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']");
        confirmPassword = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']");
        cancelButton = By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='oxd-form-actions']/button[1]");
        saveButton = By.xpath("//button[@type='submit']");
        errorReq = By.cssSelector("span.oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message:nth-child(3)");
    }

    //clicks
    public void clickAdminLabel(){
        clickAdminLBL();
    }
    public void clickAddButton(){
        clickAdd();
    }
    public void clickCancelButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton)).click();
    }
    public void clickSaveButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton)).click();
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

    //set values
    public void setValuesAddForm(String empName, String uName, String pass, String cPass){
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName)).sendKeys(empName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).sendKeys(uName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(cPass);
    }

    //add form open
    public boolean addFormOpen(){
        boolean uiDirect = false;
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser";
        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=expectedURL.equals(currentURL);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }
        return uiDirect;
    }



}
