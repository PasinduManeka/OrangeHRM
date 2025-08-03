package com.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        userName = By.xpath("//div[@class='oxd-form-row']//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
        password = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']");
        confirmPassword = By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']");
        cancelButton = By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='oxd-form-actions']/button[1]");
        saveButton = By.xpath("//button[@type='submit']");
        errorReq = By.cssSelector("span.oxd-input-field-error-message");
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

    public void selectEmployeeName(String input, String selectItem)throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName)).sendKeys(input);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']//span[normalize-space(text())='"+selectItem+"']"))).click();
    }

    //set values
    public void setValuesAddForm(String uName, String pass, String cPass){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName)).sendKeys(empName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).sendKeys(uName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(cPass);
    }

//    public void setValueAddForm(String name){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(name);
//    }

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

    //elements availability
    public boolean addFormElementAvailable(){
        try{
            boolean userrole = isElementAvailable(wait,UserRole);
            boolean status = isElementAvailable(wait,Status);
            boolean employeename = isElementAvailable(wait, employeeName);
            boolean username = isElementAvailable(wait,userName);
            boolean Password = isElementAvailable(wait, password);
            boolean ConPass = isElementAvailable(wait,confirmPassword);
            boolean cancelBtn = isElementAvailable(wait, cancelButton);
            boolean saveBtn = isElementAvailable(wait,saveButton);


//            return userrole && status && employeename && username && Password && ConPass;
            return userrole && status && employeename && username && Password && ConPass && cancelBtn && saveBtn;
        }catch (Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    private boolean isElementAvailable(WebDriverWait wait, By locator){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    //empty form
    public boolean emptyValidation(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorReq));
            List<WebElement> alertMsg = driver.findElements((errorReq));
            List<String> alertTxts = alertMsg.stream()
                    .map(WebElement::getText).filter(text -> !text.isBlank())
                    .map(String::trim).toList();

            long reqMsgCount = alertTxts.stream().filter(text-> ((String) text).equalsIgnoreCase("Required")).count();;

//            long reqMsgCount = alertMsg.stream()
//                    .filter((String text) -> text.equalsIgnoreCase("Required"))
//                    .count();

//            System.out.println("Count:"+reqMsgCount);

            return reqMsgCount>=1;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    //already taken
    public boolean alreadyTakenUserName(){
        try {
            String errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(errorReq)).getText().trim();
            String expectedElement = "Already exists";

            System.out.println("Error Text: "+errorText);

            return errorText.equals(expectedElement);
        }catch (Exception e){
            System.out.println("Error: "+e);
            return false;
        }

    }

}
