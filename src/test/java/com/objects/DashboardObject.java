package com.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardObject {
    WebDriver driver;
    WebDriverWait wait;

    By sideBar;
    By searchBox;
    By admin;
    By pim;
    By leave;
    By time;
    By recruitment;
    By myInfo;
    By performance;
    By dashboard;
    By directory;
    By maintanance;
    By claim;
    By buzz;

    public DashboardObject(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;

        initElelment();
    }

    public void initElelment(){
        sideBar = By.className("oxd-sidepanel");
        searchBox = By.className("oxd-main-menu-search");
        admin = By.xpath("//span[normalize-space()='Admin']");
        pim = By.xpath("//span[normalize-space()='PIM']");
        leave = By.xpath("//span[normalize-space()='Leave']");
        time = By.xpath("//span[normalize-space()='Time']");
        recruitment = By.xpath("//span[normalize-space()='Recruitment']");
        myInfo = By.xpath("//span[normalize-space()='My Info']");
        performance = By.xpath("//span[normalize-space()='Performance']");
        dashboard = By.xpath("//span[normalize-space()='Dashboard']");
        directory = By.xpath("//span[normalize-space()='Directory']");
        maintanance = By.xpath("//span[normalize-space()='Maintenance']");
        claim = By.xpath("//span[normalize-space()='Maintenance']");
        buzz = By.xpath("//span[normalize-space()='Buzz']");
    }

    //clicks
    public void clickAdminLable() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(admin));
    }

    public void clickPIMLable() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(pim));
    }

    public void cliickLeaveLable() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(leave));
    }

    public void clickTimeLable() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(time));
    }

    public void clickRecruitmentLable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recruitment));
    }

    public void clickMyInfoLable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(myInfo));
    }

    public void clickPerformanceLable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(performance));
    }

    public void clickDashboardLable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboard));
    }

    public void clickDirectory() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(directory));
    }

    public void clickMaintenance() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(maintanance));
    }

    public void clickClaimLable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(claim));
    }

    public void clickBuzzLable() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buzz));
    }

    //Elements available
    public boolean sidebarElementsAvailable(){
        try{
            boolean dashboardA = isElementPresent(wait, dashboard);
            boolean sidebar = isElementPresent(wait,sideBar);
		    boolean searchfield = isElementPresent(wait,searchBox);
		    boolean adminLable = isElementPresent(wait,admin);
		    boolean pimLable = isElementPresent(wait,pim);
		    boolean leaveLable = isElementPresent(wait,leave);
		    boolean timeLable = isElementPresent(wait,time);
		    boolean recruitmentLable = isElementPresent(wait,recruitment);
		    boolean myInfoLable = isElementPresent(wait, myInfo);
		    boolean performanceLable = isElementPresent(wait, performance);
		    boolean dashboardLable = isElementPresent(wait, dashboard);
		    boolean directoryLable = isElementPresent(wait, directory);
		    boolean maintananceLable = isElementPresent(wait, maintanance);
		    boolean claimLable = isElementPresent(wait, claim);
		    boolean buzzLable = isElementPresent(wait, buzz);

		return sidebar && searchfield && adminLable && pimLable && leaveLable && timeLable && recruitmentLable
				&& myInfoLable && performanceLable && dashboardLable && directoryLable && maintananceLable
				&& claimLable && buzzLable;
        }catch (Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    private boolean isElementPresent(WebDriverWait wait, By locater ){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
            return true;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }

    //check page direction
    public boolean checkAdminDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkPIMDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkLeaveDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkTimeDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/time/viewEmployeeTimesheet";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkRecruitmentDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkMyInfoDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkPerformanceDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkDashboardDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkDirectoryDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkMaintenanceDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/maintenance/purgeEmployee";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkClaimDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

    public boolean checkBuzzDirect(){
        boolean uiDirect = false;
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";

        try{
            String currentURL = driver.getCurrentUrl();
            uiDirect=currentURL.equals(expectedUrl);
        }catch(Exception e){
            System.out.println("Error: "+e);
            uiDirect=false;
        }

        return uiDirect;
    }

}
