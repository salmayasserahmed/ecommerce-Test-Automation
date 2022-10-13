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
import org.openqa.selenium.support.ui.Select;
import pages.P03_Home;

import java.util.Locale;

public class S06_SelectCategory {
    WebDriver driver;
    P03_Home homePage;
    Actions actions;

    @Before("@Category")
    public void beforeCategory() throws InterruptedException
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

    @When("^user hoovers over \"(.*)\"$")
    public void hoover(String category) throws InterruptedException {
        WebElement menu = homePage.getElementByPartialText(category);
        Thread.sleep(1000);
        actions.moveToElement(menu);
        Thread.sleep(2000);
        actions.click().perform();

    }

    @And("^user chooses \"(.*)\"$")
    public void selectCategory(String category) throws InterruptedException {
        WebElement child = homePage.getElementByPartialText(category);
        Thread.sleep(1000);
        actions.moveToElement(child);

    }

    @And("clicks on his choice")
    public void Select()
    {
        actions.click().build().perform();
    }

    @Then("^he is redirected to \"(.*)\" page$")
    public void redirect(String category)
    {
    String expectedUrl = "https://demo.nopcommerce.com/"+category.toLowerCase(Locale.ROOT);
    String actualUrl = this.driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
    @After("@Category")
    public void afterCategory()
    {
        this.driver.quit();
    }
}
