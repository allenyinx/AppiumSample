package com.android.appiumtest.locator;

import org.openqa.selenium.By;

public class LoginLocator {

    private String packagePrefix = "com.hellochat.HelloChat:id/";
    public By loginText_by = By.xpath("//android.widget.TextView[contains(@text,'Log in')]");

    public By userName_by = By.xpath("//android.widget.EditText[contains(@password,'false')]");
    public By password_by = By.xpath("//android.widget.EditText[contains(@password,'true')]");
    public By loginBtn_by = By.xpath("//android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.TextView");
    public By progressBar_by = By.className("android.widget.ProgressBar");


}
