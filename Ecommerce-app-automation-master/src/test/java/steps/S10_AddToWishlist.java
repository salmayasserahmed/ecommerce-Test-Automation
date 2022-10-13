package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.P03_Home;

public class S10_AddToWishlist {

    WebDriver driver;
    P03_Home homePage;
    @Before("@Wishlist")
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

    @When("^user navigates to \"(.*)\" wish$")
    public void chooseItemWishlist(String url) throws InterruptedException {
        this.driver.navigate().to(url);
        Thread.sleep(2000);

    }
    @And("clicks on add to wishlist")
    public void addToWishlist() throws InterruptedException {
        homePage.getElementByCss("#add-to-wishlist-button-38").click();
        Thread.sleep(2000);
    }

    @Then("wishlist success message appears")
    public void wishlistSuccess()
    {
        String expected ="The product has been added to your wishlist";
        String actual = homePage.getElementByPath("//div/p[@class=\"content\"]").getText();
        Assert.assertTrue(expected.contains(actual));
    }
    @After("@Wishlist")
    public void afterCart()
    {
        this.driver.quit();
    }
}
