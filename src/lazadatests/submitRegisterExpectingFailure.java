/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lazadatests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Duc
 */
public class submitRegisterExpectingFailure {
    private final WebDriver driver;
    
    public submitRegisterExpectingFailure(WebDriver driver) {
        this.driver = driver;
    }
    
    //elements xpath
    By registerLocator = By.xpath("//li/span[starts-with(normalize-space(),'Đăng ký')]");
    By errorMessage = By.xpath("//span[@class='error-password s-error msg error-display']");
    By Popup = By.xpath("//table//a[@class='popup-banner__button-close']");
    
    //elements id
    private WebElement RegistrationForm_first_name;
    private WebElement RegistrationForm_email;
    private WebElement RegistrationForm_password;
    private WebElement send;
    
    //validate error message in register new account form
    public submitRegisterExpectingFailure submitForm(String hoten, String email, String matkhau, String text) {
//        closePopup();
        accessRegisterForm();
        typeHoten(hoten);
        typeEmail(email);
        typeMatkhau(matkhau);
        
        return submit(text);    
    }
    
    //access register form
    public submitRegisterExpectingFailure accessRegisterForm() {
        driver.findElement(registerLocator).click();
        
        return this;
    }
    
    //input hoten
    public submitRegisterExpectingFailure typeHoten(String hoten) {
        RegistrationForm_first_name.sendKeys(hoten);
        
        return this;
    }
    
    //input email
    public submitRegisterExpectingFailure typeEmail(String email) {
        RegistrationForm_email.sendKeys(email);
        
        return this;
    }
    
    //input matkhau
    public submitRegisterExpectingFailure typeMatkhau(String matkhau) {
        RegistrationForm_password.sendKeys(matkhau);
        
        return this;
    }
    
    //submit form
    public submitRegisterExpectingFailure submit(String text) {
        send.click();
        getErrorMessage(text);
        
        return new submitRegisterExpectingFailure(driver);
    }
    
    //verify error message
    public submitRegisterExpectingFailure getErrorMessage(String text) {
        String currentText = driver.findElement(errorMessage).getText();
        if (currentText.equals(text)) {
            System.out.println("1st test: Error message is as expected: " + currentText + " :Passed");
        }
        else {
            System.out.println("1st test: Error message is not as expected: " + currentText + " :Failed");
        }
        
        return this;
    }

    //close ads
    public submitRegisterExpectingFailure closePopup() {
        driver.findElement(Popup).click();
        
        return this;
    }
}