package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class RegisterTest extends BaseTest {

    String baseurl="https://parabank.parasoft.com/parabank/index.htm";

    @Before
    public void lunchbrowser(){
        openBrowser(baseurl);
    }

    @Test
    public void RegisterAccountSuccessfully(){
        driver.findElement(By.xpath("//div/div/div[@id='loginPanel']/p/a[text()='Register']")).click();
        driver.findElement(By.id("customer.firstName")).sendKeys("Nirali");
        driver.findElement(By.id("customer.lastName")).sendKeys("Patel");
        driver.findElement(By.id("customer.address.street")).sendKeys("abc,nr.local road");
        driver.findElement(By.id("customer.address.city")).sendKeys("surat");
        driver.findElement(By.id("customer.address.state")).sendKeys("Gujarat");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("394101");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("123456789");
        driver.findElement(By.id("customer.ssn")).sendKeys("asdf");
        driver.findElement(By.id("customer.username")).sendKeys("Nirali");
        driver.findElement(By.id("customer.password")).sendKeys("1234");
        driver.findElement(By.id("repeatedPassword")).sendKeys("1234");
        driver.findElement(By.className("button")).click();
    }

    @After
    public void teardown(){
        //closeBrowser();
    }
}
