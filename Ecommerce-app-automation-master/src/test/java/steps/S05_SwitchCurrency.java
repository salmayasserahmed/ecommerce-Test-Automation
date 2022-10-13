package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.P03_Home;

public class S05_SwitchCurrency {

    WebDriver driver;
    P03_Home homePage;
    Select currencies;

    @Before("@Switch")
    public void beforeSwitch() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);
         currencies = new Select(homePage.getElementByID("customerCurrency"));


    }
    @When("^current currency is \"(.*)\"$")
    public void currCurrencyVisible(String currency)
    {

        Assert.assertEquals(currencies.getFirstSelectedOption().getText(),currency);
    }

    @And("^user chooses to switch to \"(.*)\"$")
    public void switchCurr(String currency) throws InterruptedException {
    currencies.selectByVisibleText(currency);
        Thread.sleep(3000);

    }

    @Then("^switch is done successfully and \"(.*)\" is visible$")
    public void successfullSwitch(String currency)
    {
        String expectedCurr = currency;
        String actualCurr = homePage.getElementByCss("option[selected]").getText();
        Assert.assertEquals(expectedCurr.contains(actualCurr),true);
    }


    @After("@Switch")
    public void afterSwitch()
    {
        this.driver.quit();
    }
}
