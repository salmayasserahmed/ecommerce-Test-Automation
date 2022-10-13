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

public class S11_AddToComparelist {
    WebDriver driver;
    P03_Home homePage;
    @Before("@Comparelist")
    public void beforeComparelist() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);
    }

    @When("^user navigates to \"(.*)\" compare")
    public void chooseItemComparelist(String url) throws InterruptedException {
        this.driver.navigate().to(url);
        Thread.sleep(2000);

    }
    @And("clicks on add to compare list")
    public void addToComparelist() throws InterruptedException {
        homePage.getElementByPath("//*[@id=\"product-details-form\"]/div[2]/div[1]/div[2]/div[9]/div[2]/button").click();
        Thread.sleep(2000);
    }

    @Then("compare list success message appears")
    public void ComparelistSuccess()
    {
        String expected ="The product has been added to your product comparison";
        String actual = homePage.getElementByPath("//div/p[@class=\"content\"]").getText();
        Assert.assertTrue(expected.contains(actual));
    }
    @After("@Comparelist")
    public void afterComparelist()
    {
        this.driver.quit();
    }
}
