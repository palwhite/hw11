package laptopsandnotebooks;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LaptopsAndNotebooksTest extends BaseTest {

    String baseurl="http://tutorialsninja.com/demo/index.php?";

    @Before
    public void openbrowser(){

        openBrowser(baseurl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully(){
        Actions actions= new Actions(driver);
        WebElement laptopandnotebookpagepage=driver.findElement(By.xpath("//a[text()='Laptops & Notebooks']"));
        actions.moveToElement(laptopandnotebookpagepage).build().perform();
        driver.findElement(By.xpath("//a[text()='Show AllLaptops & Notebooks']")).click();

        WebElement dropdown= driver.findElement(By.id("input-sort"));
        dropdown.click();
        Select select=new Select(dropdown);
        select.selectByValue("https://tutorialsninja.com/demo/index.php?route=product/category&path=18&sort=p.price&order=DESC");

        verifyThatUserPlaceOrderSuccessfully();
    }

    public void verifyThatUserPlaceOrderSuccessfully(){

        driver.findElement(By.xpath("//a[text()='MacBook']")).click();
        String macbooktext=driver.findElement(By.xpath("//div/div/div/h1[text()='MacBook']")).getText();
        String actualmacbooktext="MacBook";
        Assert.assertEquals("Macbook validation",macbooktext,actualmacbooktext);

        WebElement button= driver.findElement(By.cssSelector("button#button-cart"));
        button.click();
        Wait<WebDriver> wait= new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(button));

        String actualsucesstxt=driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible")).getText();
        String expectedsucesstxt="Success: You have added MacBook to your shopping cart!\n" +
                "×";
        Assert.assertEquals("success validation",expectedsucesstxt,actualsucesstxt);

        driver.findElement(By.xpath("//div/a[text()='shopping cart']")).click();

        String expectedshoppingtxt =driver.findElement(By.cssSelector("div#content h1")).getText();
        String actualshoppingtxt="Shopping Cart  (0.00kg)";
        Assert.assertEquals("Shopping validation",expectedshoppingtxt,actualshoppingtxt);

        driver.findElement(By.xpath("//td/div/input")).clear();
        driver.findElement(By.xpath("//td/div/input")).sendKeys("2");

        driver.findElement(By.xpath("//td/div/span/button[@class='btn btn-primary']")).click();
        String expectedupdatetxt=driver.findElement(By.xpath("//body/div[2]/div[1]")).getText();
        String actualupdatetxt="Success: You have modified your shopping cart!\n" +
                "×";
        Assert.assertEquals("validation",expectedupdatetxt,actualupdatetxt);

        String expectedtotal=driver.findElement(By.xpath("//div/table/tbody/tr/td[6]")).getText();
        String actualtotal="$1,204.00";
        Assert.assertEquals("Total validation",expectedtotal,actualtotal);
    }

    @After
    public void teardown(){

       // closeBrowser();
    }

}
