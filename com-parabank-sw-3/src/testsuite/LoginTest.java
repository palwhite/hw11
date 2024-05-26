package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    String baseurl="https://parabank.parasoft.com/parabank/index.htm";

    @Before
    public void lunchbrowser(){
        openBrowser(baseurl);
    }

    @Test
    public void LoginSuccessfullyWithValidCredentials(){

        driver.findElement(By.name("username")).sendKeys("Nirali");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.className("button")).click();

    }
    @Test
    public void verifyTheErrorMessage(){
        driver.findElement(By.name("username")).sendKeys("rali");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.className("button")).click();
    }

    @After
    public void teardown(){
       // closeBrowser();
    }
}
