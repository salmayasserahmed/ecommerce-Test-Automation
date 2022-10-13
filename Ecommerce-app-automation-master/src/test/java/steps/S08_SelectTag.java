package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.P03_Home;

import java.util.Locale;

public class S08_SelectTag {
    WebDriver driver;
    P03_Home homePage;
    Actions actions;

    @Before("@Tag")
    public void beforeTag() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(3000);
        homePage = new P03_Home(this.driver);
        actions = new Actions(this.driver);
    }

    @When("^user clicks \"(.*)\" from \"(.*)\" and redirected correctly$")
    public void chooseCategory(String subCategory, String category) throws InterruptedException {
        WebElement menu = homePage.getElementByPartialText(category);
        Thread.sleep(1000);
        actions.moveToElement(menu);
        Thread.sleep(2000);
        actions.click().perform();
        WebElement child = homePage.getElementByPartialText(subCategory);
        Thread.sleep(1000);
        actions.moveToElement(child);
        actions.click().build().perform();
        String expectedUrl = "https://demo.nopcommerce.com/"+subCategory.toLowerCase(Locale.ROOT);
        String actualUrl = this.driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

    }
    @When("^user clicks \"(.*)\" category$")
    public void chooseCategory(String category) throws InterruptedException {
        WebElement menu = homePage.getElementByPartialText(category);
        Thread.sleep(1000);
        actions.moveToElement(menu);
        Thread.sleep(2000);
        actions.click().perform();
        actions.click().build().perform();

    }
    @And("^chooses \"(.*)\" from popular tags$")
    public void chooseTag(String tag) throws InterruptedException {
        homePage.getElementByPartialText(tag).click();
        Thread.sleep(2000);


    }
    @Then("^gets redirected to \"(.*)\" page$")
    public void redirectTag(String tag)
    {
        String expectedUrl = "https://demo.nopcommerce.com/"+tag.toLowerCase(Locale.ROOT);
        String actualUrl = this.driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
    @After("@Tag")
    public void afterTag()
    {
        this.driver.quit();
    }
}
