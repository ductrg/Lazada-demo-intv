/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lazadatests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Duc
 */
public class CompareProductInfo {
     private final WebDriver driver;
    
    public CompareProductInfo(WebDriver driver) {
        this.driver = driver;
    }
    
    //elements xpath
    By deliveryTime = By.xpath("//span[@class='deliveryTime strong']");
    By nameProdInCart = By.xpath("//div[@class='productdescription']");
    By priceProdInCart = By.xpath("//td[@class='width_15 price center']/span[1]");
    By cartPage = By.xpath("//div[@class='nyroModalCont']");
    
    //elements id
    private WebElement prod_title;
    private WebElement special_price_box;    
    private WebElement AddToCart;
    
    //Product information from product detail page
    private String prodNameDetail = null;
    private String prodPriceDetail = null;
    private String prodDeliveryDetail = null;
    
    //validate product information (name/price/delivery days) between product detail page and cart page
    public CompareProductInfo compareInfo() {
        getProdInfoInDetailPage();
        accessCartPage();
        
        return compare();
}
    
    //get Product name, price and delivery time from detail page
    public CompareProductInfo getProdInfoInDetailPage() {
        prodNameDetail = prod_title.getText();
        prodPriceDetail = special_price_box.getText();
        prodDeliveryDetail = driver.findElement(deliveryTime).getText();
        
        return this;
    }
    
    //access cart page
    public CompareProductInfo accessCartPage() {
        //scroll to the price element, in order to click button 'mua ngay'
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", special_price_box);
        
        AddToCart.click();
        
        //wait 3s
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        
        return this;
    }
    
    //get Product information from detail page and do the comparison
    public CompareProductInfo compare() {      
        //get product information from cart page
        String nameInCart = driver.findElement(nameProdInCart).getText();
        String priceInCart = driver.findElement(priceProdInCart).getText();
        String cartSrc = driver.findElement(cartPage).getAttribute("innerHTML");
        boolean deliveryInCart = cartSrc.contains(prodDeliveryDetail);
        
        //do comparion
        if (!prodNameDetail.equals(nameInCart)) {
            System.out.println("2nd test: name is not the same: " + prodNameDetail + " vs " + nameInCart + " :Failed");
        }
        else {
            System.out.println("2nd test: name is the same: " + prodNameDetail + " vs " + nameInCart + " :Passed");
        }
        if (!prodPriceDetail.equals(priceInCart)) {
            System.out.println("2nd test: price is not the same: " + prodPriceDetail + " vs " + priceInCart + " :Failed");
        }
        else {
            System.out.println("2nd test: price is the same: " + prodPriceDetail + " vs " + priceInCart + " :Passed");
        }
        if (!deliveryInCart) {
            System.out.println("2nd test: delivery time is not the same: " + prodDeliveryDetail + " :Failed");
        }
        else {
            System.out.println("2nd test: delivery time is the same: " + prodDeliveryDetail + " :Passed");
        }
        
        return new CompareProductInfo(driver);
    }
}