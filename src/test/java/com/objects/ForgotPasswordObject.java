package com.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordObject extends LoginObject{

    WebDriver driver = null;
    WebDriverWait wait= null ;

    By formElement;
    By formTitle;
    By lable;
    By inputField;
    By cancelBTN;
    By resetPassowrdBTN;
    By error;
    By resetPassSucc;

    public ForgotPasswordObject(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
        this.driver = driver;
        this.wait = wait;

        initElements();
    }

    private void initElements() {
        formElement = By.className("orangehrm-card-container");
        formTitle = By.className("orangehrm-forgot-password-title");
        lable = By.xpath("//label[normalize-space()='Username']");
        inputField = By.name("username");
//		cancelBTN = By.className("oxd-text oxd-text--h6 orangehrm-forgot-password-title");
        cancelBTN = By.xpath("//button[normalize-space()='Cancel']");
        resetPassowrdBTN = By.xpath("//button[normalize-space()='Reset Password']");
        error = By.className("oxd-input-field-error-message");
        resetPassSucc = By.className("oxd-text--p");
    }

    //set values
    public void setValues(String userName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputField)).sendKeys(userName);
    }

    //clicks
    public void clickCancel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBTN)).click();
    }

    public void clickReset() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPassowrdBTN)).sendKeys(Keys.RETURN);
    }

    public void clickForgotPassword() {
        setForgotPassword();
    }

    //open forget password
    public boolean isOpenForgotPassword() {
        boolean uiDirect = false;
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";

        try {
            String currentURL = driver.getCurrentUrl();
            uiDirect = currentURL.equals(expectedURL);

        }catch(Exception e) {
            uiDirect = false;
            System.out.println("error: "+e);
        }

        return uiDirect;
    }

    //cancel button function
    public boolean isOpenLogin() {
        boolean uiDirect = false;
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

        try {
            String currentURL = driver.getCurrentUrl();
            System.out.println(currentURL);
            uiDirect = currentURL.equals(expectedURL);

        }catch(Exception e) {
            uiDirect = false;
            System.out.println("error: "+e);
        }

        return uiDirect;
    }

    //element present
    public boolean elementsPresent() {
        boolean form = isElementPresent(wait, formElement);
        boolean formTitile = isElementPresent(wait, formTitle);
        boolean lableName = isElementPresent(wait, lable);
        boolean field = isElementPresent(wait, inputField);
        boolean cancel = isElementPresent(wait, cancelBTN);
        boolean resetBTN = isElementPresent(wait, resetPassowrdBTN);

        return form && formTitile && lableName && field && cancel && resetBTN;

    }

    private boolean isElementPresent(WebDriverWait wait, By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }catch(Exception e) {
            System.out.println("error: "+e);
            return false;
        }
    }

    //element text values
    public Map<String, String> getStaticTexts() {
        Map<String, String> texts = new HashMap();
        texts.put("heading",driver.findElement(formTitle).getText().trim());
        texts.put("username",driver.findElement(lable).getText().trim());
        texts.put("cancel",driver.findElement(cancelBTN).getText().trim());
        texts.put("Reset", driver.findElement(resetPassowrdBTN).getText().trim());
        return texts;
    }

    //empty form submission
    public boolean isEmptyField() {
        try {
            boolean reqField = isElementPresent(wait,error);
            return reqField;
//			wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        }catch(Exception e) {
            System.out.println("error: "+e);
            return false;

        }
    }

    //successful password reset
    public boolean isSuccessfulPasswordReset() {

        boolean uiDirect = false;
        boolean successText= false;

        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset";

        try {
            String currentURL = driver.getCurrentUrl();

            uiDirect = currentURL.equals(expectedURL);
            successText = isElementPresent(wait,resetPassSucc);
        }catch(Exception e) {
            uiDirect = false;
            successText = false;
            System.out.println("error: "+e);

        }

        return uiDirect && successText;
    }


}
