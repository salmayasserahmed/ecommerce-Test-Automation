package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.de.Wenn;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.P02_Login;
import pages.P03_Home;
import pages.P05_Cart;
import pages.P06_Checkout;

public class S12_CreateOrder {

    WebDriver driver;
    P05_Cart cartPage;
    P03_Home homePage;
    P06_Checkout checkoutPage;
    P02_Login loginPage;
    Select countries;
    WebDriverWait wait;



    @Before("@CreateOrder")
    public void beforeCreateOrder() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        cartPage = new P05_Cart(this.driver);
        homePage = new P03_Home(this.driver);
        checkoutPage = new P06_Checkout(this.driver);


    }
    @When("^user logs in with \"(.*)\", \"(.*)\"$")
    public void loginFirst(String email, String pass) throws InterruptedException {
        driver.navigate().to("https://demo.nopcommerce.com/login");
        loginPage = new P02_Login(this.driver);
        loginPage.getElementByID("Email").sendKeys(email);
        Thread.sleep(1000);
        loginPage.getElementByID("Password").sendKeys(pass);
        Thread.sleep(1000);
        loginPage.getElementByPath("//div/button[@type=\"submit\"]").click();
        Thread.sleep(1000);

    }
    @Then("^user clicks on checkout button from \"(.*)\" and accepts terms of service$")
    public void proceedToCheckOut(String url) throws InterruptedException {
        this.driver.navigate().to(url);
        homePage.getElementByID("add-to-cart-button-18").click();
        Thread.sleep(2000);
        homePage.getElementByPath("//a[@href=\"/cart\"]").click();
        Thread.sleep(1000);
        cartPage.getElementByID("termsofservice").click();
        Thread.sleep(1000);
        cartPage.getElementByID("checkout").click();
        Thread.sleep(1000);
    }
    @And("he is redirected to checkout page")
    public void authCheckout()
    {
        String expectedUrl= "https://demo.nopcommerce.com/onepagecheckout";
        String actualUrl = this.driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl));
    }
    @And("^enter \"(.*)\" \"(.*)\" \"(.*)\" \"(.*)\" \"(.*)\" \"(.*)\" \"(.*)\"$")
    public void enterInfo(String fName, String lName, String country, String city, String address, String zip, String phone)throws InterruptedException
    {
        checkoutPage.getElementByID("BillingNewAddress_FirstName").clear();
        checkoutPage.getElementByID("BillingNewAddress_FirstName").sendKeys(fName);

        checkoutPage.getElementByID("BillingNewAddress_LastName").clear();
        checkoutPage.getElementByID("BillingNewAddress_LastName").sendKeys(lName);

        countries = new Select(checkoutPage.getElementByID("BillingNewAddress_CountryId"));
        countries.selectByVisibleText(country);

        checkoutPage.getElementByID("BillingNewAddress_City").clear();
        checkoutPage.getElementByID("BillingNewAddress_City").sendKeys(city);

        checkoutPage.getElementByID("BillingNewAddress_Address1").clear();
        checkoutPage.getElementByID("BillingNewAddress_Address1").sendKeys(address);

        checkoutPage.getElementByID("BillingNewAddress_ZipPostalCode").clear();
        checkoutPage.getElementByID("BillingNewAddress_ZipPostalCode").sendKeys(zip);

        checkoutPage.getElementByID("BillingNewAddress_PhoneNumber").clear();
        checkoutPage.getElementByID("BillingNewAddress_PhoneNumber").sendKeys(phone);
        checkoutPage.getElementByPath("//*[@id=\"billing-buttons-container\"]/button[4]").click();
        Thread.sleep(1000);

    }
    @And("Fills all the steps and clicks continue")
    public void completeSteps() throws InterruptedException {
        checkoutPage.getElementByPath("//*[@id=\"shipping-method-buttons-container\"]/button").click();
        Thread.sleep(1000);
        checkoutPage.getElementByPath("//*[@id=\"payment-method-buttons-container\"]/button").click();
        Thread.sleep(1000);
        checkoutPage.getElementByPath("//*[@id=\"payment-info-buttons-container\"]/button").click();
        Thread.sleep(1000);
        checkoutPage.getElementByPath("//*[@id=\"confirm-order-buttons-container\"]/button").click();
        Thread.sleep(1000);
    }
    @Then("Your order has been successfully processed! appears")
    public void successfulOrder()
    {
        String expectedMsg="Your order has been successfully processed!";
        String actualMsg = checkoutPage.getElementByPath("//div[@class=\"title\"]/strong").getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));
    }
    @Then("he is redirected to login page")
    public void unauth()
    {
        try{
            cartPage.getElementByPartialText("Log out");
        }catch(NoSuchElementException e)
        {
            String expectedUrl= "https://demo.nopcommerce.com/login";
            String actualUrl = this.driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrl));
        }
    }

    @After("@CreateOrder")
    public void afterCreateOrder()
    {
        this.driver.quit();
    }
}
