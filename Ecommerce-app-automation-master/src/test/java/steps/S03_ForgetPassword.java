package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.P02_Login;
import pages.P03_Home;
import pages.P04_ForgetPassword;

public class S03_ForgetPassword {
    WebDriver driver;
    P03_Home homePage;
    P02_Login loginPage;
    P04_ForgetPassword forgetPassPage;

    @Before("@ResetPass")
    public void beforeReset() throws InterruptedException
    {
        String path = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        forgetPassPage = new P04_ForgetPassword(driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.nopcommerce.com/");
        Thread.sleep(2000);
        homePage = new P03_Home(this.driver);
        loginPage = new P02_Login(this.driver);
        homePage.getElementByClass("ico-login").click();
        Thread.sleep(1000);
        loginPage.getElementByPath("//span[@class=\"forgot-password\"]").click();
        Thread.sleep(1000);
    }

    @When("^user asks to reset with email \"(.*)\"$")
    public void resetWithEmail(String email) throws InterruptedException {
        forgetPassPage.getElementByID("Email").sendKeys(email);
        Thread.sleep(1000);
        forgetPassPage.getElementByPath("//button[@name=\"send-email\"]").click();
    }
    @Then("\"Email with instructions has been sent to you\" is displayed")
    public void validReset()
    {
//valid case
        try{
            String expectedResult = "Email with instructions has been sent to you" ;
            String actualResult = forgetPassPage.getElementByPath("//p[@class=\"content\"]").getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }catch(NoSuchElementException e)
        {
            System.out.println("Exception in valid login");
        }
    }

    @Then("\"Wrong Email\" is displayed")
    public void invalidReset()
    {
//invalid case
        try{
            String expectedResult = "Wrong email" ;
            String actualResult = forgetPassPage.getElementByPath("//span[@class=\"field-validation-error\"]").getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }catch(NoSuchElementException e)
        {
            System.out.println("Exception in invalid login");
        }
    }

    @Then("\"Email not found\" is displayed")
    public void notFoundReset()
    {
        //non exist case
        try{
            String expectedResult = "Email not found" ;
            String actualResult = forgetPassPage.getElementByPath("//p[@class=\"content\"]").getText();
            Assert.assertTrue(actualResult.contains(expectedResult));
        }catch(NoSuchElementException e)
        {
            System.out.println("Exception in not found email login");
        }
    }

    @After("@ResetPass")
    public void afterReset()
    {
        this.driver.quit();
    }

}
