package desktops;

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

public class DesktopsTest extends BaseTest {
    String baseurl="http://tutorialsninja.com/demo/index.php?";

    @Before
    public void openbrowser(){

        openBrowser(baseurl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder(){

        Actions actions= new Actions(driver);
        WebElement dekstoppage=driver.findElement(By.xpath("//a[text()='Desktops']"));
        actions.moveToElement(dekstoppage).build().perform();
        driver.findElement(By.xpath("//a[text()='Show AllDesktops']")).click();

        WebElement dropdown= driver.findElement(By.id("input-sort"));
        dropdown.click();
        Select select=new Select(dropdown);
        select.selectByValue("https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC");

        driver.findElement(By.xpath("//header/div/div/div/div/h1/a")).click();

        verifyProductAddedToShoppingCartSuccessFully();
    }

    public void verifyProductAddedToShoppingCartSuccessFully(){

        Actions actions= new Actions(driver);
        WebElement dekstoppage=driver.findElement(By.xpath("//a[text()='Desktops']"));
        actions.moveToElement(dekstoppage).build().perform();
        driver.findElement(By.xpath("//a[text()='Show AllDesktops']")).click();

        WebElement dropdown= driver.findElement(By.id("input-sort"));
        dropdown.click();
        Select select=new Select(dropdown);
        select.selectByValue("https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=ASC");
        driver.findElement(By.xpath("//h4/a[text()='HP LP3065']")).click();

        String actualdeksopstring=driver.findElement(By.xpath("//div/div/div/div/div/h1")).getText();
        String expecteddekstopstring="HP LP3065";
        Assert.assertEquals("HP LP3065 vlidation",expecteddekstopstring,actualdeksopstring);

        driver.findElement(By.cssSelector("input#input-option225")).clear();
        driver.findElement(By.cssSelector("input#input-option225")).sendKeys("2022-11-30");

        WebElement button=driver.findElement(By.cssSelector("button#button-cart"));
        button.click();

       // driver.findElement(By.id("cart-total")).click();
        Wait<WebDriver> wait= new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(button));

        String actualSuccessmsg=driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible")).getText();
        String expectedsucessmsg="Success: You have added HP LP3065 to your shopping cart!\n" +
                "×";
        Assert.assertEquals("sucess validation",actualSuccessmsg,expectedsucessmsg);

        driver.findElement(By.xpath("//div/a[text()='shopping cart']")).click();

        String actualdekstoptext=driver.findElement(By.cssSelector("div.col-sm-12 h1")).getText();
        String expecteddekstoptext= "Shopping Cart  (1.00kg)";
        Assert.assertEquals("Dekstop validation",expecteddekstoptext,actualdekstoptext);

        String actualhoaptoptext=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[2]/a")).getText();
        String expectedhplaptoptext= "HP LP3065";
        Assert.assertEquals("HP LP3065 validation",expectedhplaptoptext,actualhoaptoptext);

        String actualdeliverytexttext=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[2]/small[1]")).getText();
        String expecteddeliverydatetext= "Delivery Date:2022-11-30";
        Assert.assertEquals("deliverydate  validation",expecteddeliverydatetext,actualdeliverytexttext);

        String expectedproduct= driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[3]")).getText();
        String actualproduct="Product 21";
        Assert.assertEquals("Product 21 validation",expectedproduct,actualproduct);
    }

    @After
    public void teardown(){
        //closeBrowser();
    }
}
