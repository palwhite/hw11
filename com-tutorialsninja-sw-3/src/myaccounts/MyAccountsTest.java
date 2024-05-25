package myaccounts;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyAccountsTest extends BaseTest {

    String baseurl="http://tutorialsninja.com/demo/index.php?";

    @Before
    public void openbrowser(){

        openBrowser(baseurl);
    }

    public void selectMyAccountOptions(String option){

    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully(){

        driver.findElement(By.xpath("//div/ul/li/a/span[text()='My Account']")).click();
        WebElement loginorregister=driver.findElement(By.xpath("//div/ul/li/ul/li/a[text()='Register']"));
        loginorregister.click();
            String expectedregistertxt = driver.findElement(By.cssSelector("div#content h1")).getText();
            String actualregistertxt = "Register Account";
            Assert.assertEquals("Validation", expectedregistertxt, actualregistertxt);

            driver.findElement(By.id("input-firstname")).sendKeys("Nirali");
            driver.findElement(By.id("input-lastname")).sendKeys("Patel");
            driver.findElement(By.id("input-email")).sendKeys("np@gmail.com");
            driver.findElement(By.id("input-telephone")).sendKeys("123456789");
            driver.findElement(By.id("input-password")).sendKeys("1234");
            driver.findElement(By.id("input-confirm")).sendKeys("1234");

            driver.findElement(By.name("agree")).click();
            driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

            driver.findElement(By.xpath("//div/a[text()='Continue']")).click();

            driver.findElement(By.xpath("//div/a[text()='Logout']")).click();



    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully(){
        driver.findElement(By.xpath("//div/ul/li/a/span[text()='My Account']")).click();
        driver.findElement(By.xpath("//div/ul/li/ul/li/a[text()='Login']")).click();

        driver.findElement(By.id("input-email")).sendKeys("np@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("1234");

        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        driver.findElement(By.xpath("//div/a[text()='Logout']")).click();

        String expectedlogout=driver.findElement(By.cssSelector("div#content h1")).getText();
        String actuallogout="Account Logout";
        Assert.assertEquals("Logout validation",expectedlogout,actuallogout);

        driver.findElement(By.xpath("//div/div/a")).click();
    }

    @After
    public void teardown(){

       // closeBrowser();
    }
}
