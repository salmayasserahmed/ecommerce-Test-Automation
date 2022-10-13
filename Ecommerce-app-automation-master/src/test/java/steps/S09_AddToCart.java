package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.P03_Home;
import pages.P05_Cart;
import pages.P06_Checkout;

public class S09_AddToCart {
    WebDriver driver;
    P03_Home homePage;
    @Before("@Cart")
    public void beforeCart() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);
    }

    @When("^user navigates to \"(.*)\"$")
    public void chooseItem(String url) throws InterruptedException {
        this.driver.navigate().to(url);
        Thread.sleep(2000);

    }
    @And("clicks on ADD TO CART")
    public void addToCart() throws InterruptedException {
        homePage.getElementByCss("#add-to-cart-button-38").click();
        Thread.sleep(2000);
    }

    @Then("Success message appears")
    public void cartSuccess()
    {
        String expected ="The product has been added to your shopping cart";
        String actual = homePage.getElementByPath("//div/p[@class=\"content\"]").getText();
        Assert.assertTrue(expected.contains(actual));
    }

    @After("@Cart")
    public void afterCart()
    {
        this.driver.quit();
    }
}
