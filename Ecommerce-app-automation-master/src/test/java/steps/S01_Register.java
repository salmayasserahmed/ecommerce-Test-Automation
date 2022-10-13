package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.P01_Register;
import pages.P03_Home;


public class S01_Register {
    WebDriver driver;
    P01_Register registerPage;
    P03_Home homePage;


    @Before("@Registeration")
    public void beforeRegistration() throws InterruptedException
    {
        System.out.println(System.getProperty("user.dir"));
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        registerPage = new P01_Register(driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);
        homePage.getElementByClass("ico-register").click();
        Thread.sleep(2000);
        driver.navigate().to("https://demo.nopcommerce.com/register");


    }





    @When("^user enters \"(.*)\" in first name and \"(.*)\" in second name$")
    public void enterName(String first, String last) throws InterruptedException {
        registerPage.getElementByID("FirstName").sendKeys(first);
        registerPage.getElementByID("LastName").sendKeys(last);
        Thread.sleep(2000);


    }

    @When("^user enters \"(.*)\" in email$")
    public void enterEmail(String email) throws InterruptedException {
        registerPage.getElementByID("Email").sendKeys(email);
        Thread.sleep(2000);


    }

    @When("^user enters \"(.*)\" in password and \"(.*)\" in confirm password$")
    public void enterPassword(String pass1, String pass2) throws InterruptedException {
        registerPage.getElementByID("Password").sendKeys(pass1);
        registerPage.getElementByID("ConfirmPassword").sendKeys(pass2);
        Thread.sleep(2000);


    }

    @Then("he can register successfully and \"Your registeration completed\" messege appears")
    public void validRegister() throws InterruptedException {
        registerPage.getElementByID("register-button").click();
        Thread.sleep(1000);
        String expected = "Your registration completed";
        String actual = registerPage.getElementByClass("result").getText();

        Assert.assertTrue("Assertion fail in registeration",expected.contains(actual));
        Thread.sleep(2000);
    }

    @Then("error messeges for invalid fields appear")
    public void invalidRegister() throws InterruptedException, NoSuchElementException {
        registerPage.getElementByID("register-button").click();
        Thread.sleep(1000);
        String expectedEmail = "Wrong email";
        String expectedPassword = "Password must meet the following rules:";
        String expectedConfirmPassword = "The password and confirmation password do not match.";

        try {
            String actualEmail = registerPage.getElementByID("Email-error").getText();
            Assert.assertTrue("Error in Email", expectedEmail.contains(actualEmail));
        } catch (NoSuchElementException e) {
            System.out.println("Exception in Email");

        }
        try {
            String actualPassword = registerPage.getElementByPath("//span/p").getText();
            System.out.println(actualPassword);
            Assert.assertTrue("Error in Password", expectedPassword.contains(actualPassword));
        } catch (NoSuchElementException e1) {
            System.out.println("Exception in Password");

        }
        try{
            String actualConfirmPassword = registerPage.getElementByID("ConfirmPassword-error").getText();
            Assert.assertTrue("Error in Password Confirmation", expectedConfirmPassword.contains(actualConfirmPassword));
        }catch(NoSuchElementException e2){
            System.out.println("Exception in Confirm Password");

        }


    }

    @Then("error messeges for empty fields appear appear")
    public void emptyRegister() throws InterruptedException, NoSuchElementException
    {
        registerPage.getElementByID("register-button").click();
        Thread.sleep(1000);
        String expectedFirstName = "First name is required.";
        String expectedLastName = "Last name is required.";
        String expectedEmail = "Email is required.";
        String expectedPassword = "Password is required.";

        try{
            String actualFirstName = registerPage.getElementByID("FirstName-error").getText();
            Assert.assertTrue("Empty first name",expectedFirstName.contains(actualFirstName) );

        }catch (NoSuchElementException e) {
            System.out.println("Exception in first name");
        }
        try {
            String actualLastName = registerPage.getElementByID("LastName-error").getText();
            Assert.assertTrue("Empty Last name", expectedLastName.contains(actualLastName));
        }catch(NoSuchElementException e1) {
            System.out.println("Exception in Last name");
        }
        try{
            String actualEmail = registerPage.getElementByID("Email-error").getText();
            Assert.assertTrue("Empty Email", expectedEmail.contains(actualEmail));
        }catch(NoSuchElementException e2)
        {
            System.out.println("Exception in Email");
        }
        try{
            String actualPassword = registerPage.getElementByID("Password-error").getText();
            Assert.assertTrue("Empty Password", expectedPassword.contains(actualPassword));

        }catch(NoSuchElementException e3)
            {
                System.out.println("Exception in Password");

            }
        try{
            String actualConfirmPassword = registerPage.getElementByID("ConfirmPassword-error").getText();
            Assert.assertTrue("Empty Password Confirmation", expectedPassword.contains(actualConfirmPassword));
        }catch(NoSuchElementException e4)
        {
            System.out.println("Exception in Confirm password");

        }
    }




    @Then("error messeges for existing account appear")
    public void existingRegister() throws InterruptedException {
        registerPage.getElementByID("register-button").click();
        Thread.sleep(1000);
        String expectedEmail = "The specified email already exists\n";
        String actualEmail = registerPage.getElementByPath("//form/div/ul/li").getText();
        Assert.assertTrue(" Email exists ",expectedEmail.contains(actualEmail) );


    }
    @After("@Registeration")
    public void afterRegistration()
    {
        this.driver.quit();
    }
}

