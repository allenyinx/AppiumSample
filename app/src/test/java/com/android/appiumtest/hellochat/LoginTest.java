package com.android.appiumtest.hellochat;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class LoginTest extends ChatBaseTest {

//    @Test
    public void loginTest() throws InterruptedException {

        driver.findElement(loginLocator.loginText_by).click();
//        driver.navigate().back();
        driver.findElement(loginLocator.userName_by).sendKeys(System.getenv("CHATUSER"));
        driver.findElement(loginLocator.password_by).sendKeys(System.getenv("CHATPASSWORD"));
//        driver.navigate().back();
        driver.findElement(loginLocator.loginBtn_by).click();

        driver.findElement(loginLocator.progressBar_by);

        Thread.sleep(5000);
    }

    @Test
    public void screenshot() throws InterruptedException {

        loginTest();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginLocator.progressBar_by));

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);
    }
}