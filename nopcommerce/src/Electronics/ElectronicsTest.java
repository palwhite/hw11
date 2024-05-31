package Electronics;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElectronicsTest extends BaseTest {

    String baseurl="https://demo.nopcommerce.com/";

    @Before
    public void Setup(){

        openBrowser(baseurl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully(){

        Actions actions= new Actions(driver);
        WebElement electronicspage=driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Electronics ']"));
        actions.moveToElement(electronicspage).build().perform();
        driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li/ul/li/a[text()='Cell phones ']")).click();

        String expectedtitletxt=driver.findElement(By.xpath("//div/div/h1")).getText();
        String actualtitletxt="Cell phones";
        Assert.assertEquals("Title Validation",expectedtitletxt,actualtitletxt);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully(){

        Wait<WebDriver> wait=new WebDriverWait(driver, Duration.ofSeconds(10));

        Actions actions= new Actions(driver);

        WebElement electronicspage=driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Electronics ']"));
        actions.moveToElement(electronicspage).build().perform();
        driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li/ul/li/a[text()='Cell phones ']")).click();

        String expectedtitletxt=driver.findElement(By.xpath("//div/div/h1")).getText();
        String actualtitletxt="Cell phones";
        Assert.assertEquals("Title Validation",expectedtitletxt,actualtitletxt);

        WebElement list = driver.findElement(By.xpath("//a[text()='List']"));
        Assert.assertTrue(list.isEnabled());
        Assert.assertTrue(list.isDisplayed());
        list.click();

        for(int i =0; i<=2; i++){

            actions.sendKeys(Keys.PAGE_DOWN).build().perform();
        }
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(By.xpath("//div/h2/a[text()='Nokia Lumia 1020']"))));
        driver.findElement(By.xpath("//div/h2/a[text()='Nokia Lumia 1020']")).click();
        
        String exptitletxt=driver.findElement(By.xpath("//div/h1")).getText();
        String acttitletxt="Nokia Lumia 1020";
        Assert.assertEquals("Nokia Lumia 1020 validation",exptitletxt,acttitletxt);
    }

    @After
    public void teardown(){
      //  closeBrowser();
    }

}
