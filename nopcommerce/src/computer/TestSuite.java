package computer;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestSuite extends BaseTest {

    String baseurl="https://demo.nopcommerce.com/";

    @Before
    public void setup(){
        openBrowser(baseurl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder(){

        Wait<WebDriver> wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions= new Actions(driver);
        WebElement computerpage=driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Computers ']"));
        actions.moveToElement(computerpage).build().perform();
        driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li/ul/li/a[text()='Desktops ']")).click();

        WebElement dropdown= driver.findElement(By.id("products-orderby"));
        Select select=new Select(dropdown);
        select.selectByValue("5");

        driver.findElement(By.xpath("//div[1]/div[@class='product-item']/div[2]/div[3]/div[2]/button[1]")).click();

//        WebElement processor=driver.findElement(By.id("product_attribute_1"));
//        new Select(processor);
//        select.selectByValue("1");
    }

    @After
    public void teardown(){
        //closeBrowser();
    }
}
