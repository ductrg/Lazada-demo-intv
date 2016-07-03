/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lazadatests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Duc
 */
public class lazadaTests {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        try
        {   //1st test: validate error message in register new account form
            driver.get("http://www.lazada.vn/faq/");
            //wait 3s
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            
            submitRegisterExpectingFailure test1 = PageFactory.initElements(driver, submitRegisterExpectingFailure.class);
            test1.submitForm("abc", "abc@gmail.com", "abc123", "Mật khẩu phải có ít nhất 1 chữ số");
            
            //2nd test: validate product information (name/price/delivery days) between product detail page and cart page
            driver.get("http://www.lazada.vn/canon-eos-600d-18mp-den-ong-18-55mm-38844.html");
            //wait 3s
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            
            CompareProductInfo test2 = PageFactory.initElements(driver, CompareProductInfo.class);
            test2.compareInfo();
            
            driver.quit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            driver.quit();            
        }
    }
}