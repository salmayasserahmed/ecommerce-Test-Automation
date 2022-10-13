package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.P02_Login;
import pages.P03_Home;

public class S02_Login {
    WebDriver driver;
    P02_Login loginPage;
    P03_Home homePage;

    @Before("@Login")
    public void beforeLogin() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        loginPage = new P02_Login(driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);
        homePage.getElementByClass("ico-login").click();
        Thread.sleep(1000);

    }

    @When("^user enters \"(.*)\" in email login$")
    public void emailLogin(String email) throws InterruptedException {
        loginPage.getElementByID("Email").sendKeys(email);
        Thread.sleep(2000);
    }

    @When("^user enters \"(.*)\" in password$")
    public void passwordLogin(String pass1) throws InterruptedException {
        loginPage.getElementByID("Password").sendKeys(pass1);
        Thread.sleep(2000);
    }

    @Then("he gets redirected to home page as authenticated user")
    public void validLogin() throws InterruptedException {
        loginPage.getElementByPath("//div/button[@type=\"submit\"]").click();
        Thread.sleep(1000);
        String expectedURL = "https://demo.nopcommerce.com/";
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue("Not expected url",actualURL.contains(expectedURL));
        WebElement logoutBtn = homePage.getElementByPartialText("Log out");
        Assert.assertTrue("Not auth user",logoutBtn!=null);

    }

    @Then("he stays on the same page and error messeges for invalid login appear")
    public void invalidLogin() throws InterruptedException, NoSuchElementException {
        loginPage.getElementByPath("//div/button[@type=\"submit\"]").click();
        Thread.sleep(1000);
        String expectedURL = "https://demo.nopcommerce.com/login";
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue("Not expected url",actualURL.contains(expectedURL));
        String actualMsg = loginPage.getElementByPath("//div[@class=\"message-error validation-summary-errors\"]").getText();
        String expectedMsg = "Login was unsuccessful";
        Assert.assertTrue("Error in warning msg",actualMsg.contains(expectedMsg));

    }
    @After("@Login")
    public void afterLogin()
    {
        this.driver.quit();
    }

}
