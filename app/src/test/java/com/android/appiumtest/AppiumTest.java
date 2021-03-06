package com.android.appiumtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;


public class AppiumTest {

    //Define AndroidDriver Object
    protected WebDriver driver;

    String appPackageName = "com.hellochat.HelloChat";
    String id = ":id/";
    // id of Widget like EditText username & Password
    By userId = By.id(appPackageName + id + "email");

    By password = By.id("com.android.appiumtest:id/password");
//    By password = By.id(appPackageName + id + "password");
    //id of Widget like Button
    By login_Button = By.id("com.android.appiumtest:id/email_sign_in_button");
//    By login_Button = By.id(appPackageName + id + "email_sign_in_button");


    /**
     * Setup DesiredCapabilities and Android Driver
     *
     * @throws MalformedURLException
     */
    @Before
    public void setUp() throws MalformedURLException {

        File appDir = new File("/Users/allenyin/Downloads/");
        File app = new File(appDir, "HelloChat-prod-v1.1.9.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Appium Server Version Name check your version name of appium
        capabilities.setCapability("appium-version", "1.15.1");
        capabilities.setCapability("platformName", "Android");
        //Device Android Version
        capabilities.setCapability("platformVersion", "10.0");
        //Device name displayed on Android Monitor
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("remoteAdbHost", "host.docker.internal");
        capabilities.setCapability("adbPort", "5037");
        // Package Name of Application
        capabilities.setCapability("appPackage", appPackageName);
        //Activity Name
        capabilities.setCapability("appActivity", appPackageName + ".MainActivity");
        //Appium Running Server URL path(including IP and PORT)
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest() {
        driver.findElement(userId).sendKeys("test@xyz.com");
//        driver.navigate().back();
        driver.findElement(password).sendKeys("test1234");
//        driver.navigate().back();
        driver.findElement(login_Button).click();
        WebElement element = driver.findElement(By.id(appPackageName + id + "welcome_user"));
        assert element.getText().equals("test@xyz.com") : "Actual value is " + element.getText() + " did not match with expected value test@xyz.com";

    }


    @After
    public void End() {
        driver.quit();
    }
}