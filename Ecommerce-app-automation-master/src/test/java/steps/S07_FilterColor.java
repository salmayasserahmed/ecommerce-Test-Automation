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

import java.util.Locale;

public class S07_FilterColor {
    WebDriver driver;
    P03_Home homePage;

    @Before("@Color")
    public void beforeColor() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);
    }

    @When("^user goes to \"(.*)\"$")
    public void surf(String url) throws InterruptedException {
        this.driver.navigate().to(url);
        Thread.sleep(2000);

    }

    @And("^chooses \"(.*)\" from color attributes$")
    public void chooseColor(String color)
    {
        this.homePage.getElementByID("attribute-option-15").click();
    }
    @Then("^adidas 80s running shoes appears in results$")
    public void successFilter()
    {
    String actual =this.homePage.getElementByPath("//h2[@class=\"product-title\"]/a").getText();
    String expected = "adidas Consortium Campus 80s Running Shoes";
        Assert.assertTrue(actual.contains(expected));
    }


    @After("@Color")
    public void afterColor()
    {
        this.driver.quit();
    }
}
