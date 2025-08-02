package com.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginObject {

    WebDriver driver;
    WebDriverWait wait;

    By userName;
    By pwd;
    By submit;
    By reqError;
    By forgetPassword;

    public LoginObject(WebDriver driver,WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;

        initElements();
    }

    private void initElements(){
        userName = By.xpath("//input[@name='username']");
        pwd = By.xpath("//input[@name='password']");
        submit = By.className("orangehrm-login-button");
        reqError = By.className("oxd-input-field-error-message");
        forgetPassword = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");

    }


    //set values
    public void setValues(String username, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pwd)).sendKeys(password);
    }

    //clicks
    public void setSubmit(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(submit)).click();
    }

    public void setForgotPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(forgetPassword)).click();
    }


    //elements available
    public boolean loginElementsAvailable(){
        boolean username = isElelementPresent(wait,userName);
        boolean password = isElelementPresent(wait, pwd);
        boolean btn = isElelementPresent(wait, submit);

        return  username && password && btn;
    }

    private boolean isElelementPresent(WebDriverWait wait, By locater){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    //verify success login
    public boolean verifySuccessLogin(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        try{
            String currentURL = driver.getCurrentUrl();
//            System.out.println("Expected :"+expectedUrl+"  Current: "+currentURL);
            uiDirect = currentURL.equals(expectedUrl);
            System.out.println(uiDirect);
        }catch (Exception e){
            System.out.println("Error: "+e);
            uiDirect = false;
        }

        return uiDirect;
    }

    //empty form
    public boolean emptyValidation(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(reqError));
            List<WebElement> alertMsg = driver.findElements((reqError));
            List<String> alertTxts = alertMsg.stream()
                    .map(WebElement::getText).filter(text -> !text.isBlank())
                    .map(String::trim).toList();

            long reqMsgCount = alertTxts.stream().filter(text-> ((String) text).equalsIgnoreCase("Required")).count();;

//            long reqMsgCount = alertMsg.stream()
//                    .filter((String text) -> text.equalsIgnoreCase("Required"))
//                    .count();

            System.out.println("Count:"+reqMsgCount);

            return reqMsgCount==2;

        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }

    }

    public boolean captureOneEmptyValidation(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(reqError));
            List<WebElement> alertMsg = driver.findElements((reqError));
            List<String> alertTxts = alertMsg.stream()
                    .map(WebElement::getText).filter(text -> !text.isBlank())
                    .map(String::trim).toList();

            long reqMsgCount = alertTxts.stream().filter(text-> ((String) text).equalsIgnoreCase("Required")).count();;

            System.out.println(reqMsgCount);
            return reqMsgCount == 1;
        }catch(Exception e){
            return false;
        }
    }

    //login
    public void login(String username, String password)throws InterruptedException{
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(1000);
        setValues(username,password);
        setSubmit();
//        Thread.sleep(1000

    }


}
