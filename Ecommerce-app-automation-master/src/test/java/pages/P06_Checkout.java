package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class P06_Checkout {
    WebDriver driver;

    public P06_Checkout(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementByID(String elem)
    {

        return this.driver.findElement(By.id(elem));
    }
    public WebElement getElementByPartialText(String elem)
    {

        return this.driver.findElement(By.partialLinkText(elem));
    }
    public WebElement getElementByPath(String elem)
    {

        return this.driver.findElement(By.xpath(elem));
    }
}
