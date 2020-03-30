package com.android.appiumtest.hellochat;

import com.android.appiumtest.locator.LoginLocator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class ChatBaseTest {

    //Define AndroidDriver Object
    protected WebDriver driver;

    protected LoginLocator loginLocator;

    String appPackageName = "com.hellochat.HelloChat";

    /**
     * Setup DesiredCapabilities and Android Driver
     *
     * @throws MalformedURLException
     */
    @Before
    public void setUp() throws MalformedURLException {

        File appDir = new File(System.getenv("HOME"));
        File app = new File(appDir, "HelloChat-prod-v1.1.9.apk");

        loginLocator = new LoginLocator();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Appium Server Version Name check your version name of appium
        capabilities.setCapability("appium-version", "1.15.1");
        capabilities.setCapability("platformName", "Android");
        //Device Android Version
        capabilities.setCapability("platformVersion", "10.0");
        //Device name displayed on Android Monitor
        capabilities.setCapability("deviceName", "emulator-5554");
//        capabilities.setCapability("remoteAdbHost", "host.docker.internal");
//        capabilities.setCapability("adbPort", "5037");
        // Package Name of Application
        capabilities.setCapability("appPackage", appPackageName);
        //Activity Name
        capabilities.setCapability("appActivity", appPackageName + ".MainActivity");
        //Appium Running Server URL path(including IP and PORT)
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    public  boolean waitForPresence(AndroidDriver driver, int timeLimitInSeconds, String targetResourceId){

        boolean isElementPresent = false;
        try{
            MobileElement mobileElement =  (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\""+targetResourceId+"\")");
            WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
            wait.until(ExpectedConditions.visibilityOf(mobileElement));
            isElementPresent = mobileElement.isDisplayed();
            return isElementPresent;
        }catch(Exception e){
            isElementPresent = false;
            System.out.println(e.getMessage());
            return isElementPresent;
        } }


    public void getElementSource(WebElement webElement) {

        webElement.getAttribute("outerHTML");
    }


    @After
    public void End() {
        driver.quit();
    }
}