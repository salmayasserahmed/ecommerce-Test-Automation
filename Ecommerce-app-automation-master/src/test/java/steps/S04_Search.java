package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.P03_Home;

public class S04_Search {
    WebDriver driver;
    P03_Home homePage;

    @Before("@Search")
    public void beforeSearch() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);

    }

    @When("^user enters \"(.*)\" in search bar$")
    public void searchField(String item) throws InterruptedException {
    homePage.getElementByID("small-searchterms").sendKeys(item);
        Thread.sleep(1000);
    homePage.getElementByPath("//button[@type=\"submit\"]").click();
    }

    @Then("product results appear")
    public void successfulSearch()
    {
        WebElement exist = homePage.getElementByPath("//div[@class=\"product-selectors\"]")     ;
        Assert.assertTrue(exist!=null);
    }

    @Then("\"No products were found\" message is displayed")
    public void unSuccessfulSearch()
    {
        String expectedResult = "No products were found that matched your criteria";
        String actualResult = homePage.getElementByPath("//div[@class=\"no-result\"]").getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }
    @After("@Search")
    public void afterSearch()
    {
        this.driver.quit();
    }

}
