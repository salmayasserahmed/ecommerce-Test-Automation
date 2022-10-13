package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class P01_Register {
    WebDriver driver;

    public P01_Register(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementByID(String elem)
    {

        return this.driver.findElement(By.id(elem));
    }

    public WebElement getElementByClass(String elem)
    {

        return this.driver.findElement(By.className(elem));
    }


public WebElement getElementByPath(String elem)
{

    return this.driver.findElement(By.xpath(elem));
}
}
