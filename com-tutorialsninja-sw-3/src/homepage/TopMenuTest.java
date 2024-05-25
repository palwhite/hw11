package homepage;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TopMenuTest extends BaseTest {

    String baseurl="http://tutorialsninja.com/demo/index.php?";

    @Before
    public void openbrowser(){
        openBrowser(baseurl);
    }

    @Test
    public void selectMenu(){

        String menu="Dekstop";
        if (menu.equals("Dekstop")){
            toDesktopsPageSuccessfully();
            driver.findElement(By.xpath("//header/div/div/div/div/h1/a")).click();
            menu="laptopandnotebookpage";
        }
        if (menu.equals("laptopandnotebookpagepage")){
            toLaptopsAndNotebooksPageSuccessfully();
            driver.findElement(By.xpath("//header/div/div/div/div/h1/a")).click();
            menu="Componentspage";
        }
        if (menu.equals("Componentspage")){
            toComponentsPageSuccessfully();
            driver.findElement(By.xpath("//header/div/div/div/div/h1/a")).click();
        }

    }

    public void toDesktopsPageSuccessfully(){
        Actions actions= new Actions(driver);
        WebElement dekstoppage=driver.findElement(By.xpath("//a[text()='Desktops']"));
        actions.moveToElement(dekstoppage).build().perform();
        driver.findElement(By.xpath("//a[text()='Show AllDesktops']")).click();

        String actualdekstopmsg=driver.findElement(By.xpath("//div/div/div/h2")).getText();
        String expecteddekstopmsg= "Desktops";
        Assert.assertEquals("Dekstop message validation",expecteddekstopmsg,actualdekstopmsg);
    }


    public void toLaptopsAndNotebooksPageSuccessfully(){
        Actions actions= new Actions(driver);
        WebElement laptopandnotebookpagepage=driver.findElement(By.xpath("//a[text()='Laptops & Notebooks']"));
        actions.moveToElement(laptopandnotebookpagepage).build().perform();
        driver.findElement(By.xpath("//a[text()='Show AllLaptops & Notebooks']")).click();

        String actuallaptopandnotebookpagepagemsg=driver.findElement(By.xpath("//div/div/div/h2")).getText();
        String expectedlaptopandnotebookpagepagemsg= "Laptops & Notebooks";
        Assert.assertEquals("Laptops & Notebooks message validation",expectedlaptopandnotebookpagepagemsg,actuallaptopandnotebookpagepagemsg);
    }

    public void toComponentsPageSuccessfully(){
        Actions actions= new Actions(driver);
        WebElement Componentspage=driver.findElement(By.xpath("//a[text()='Components']"));
        actions.moveToElement(Componentspage).build().perform();
        driver.findElement(By.xpath("//a[text()='Show AllComponents']")).click();

        String actualComponentsmsg=driver.findElement(By.xpath("//div/div/div/h2")).getText();
        String expectedComponentsmsg= "Components";
        Assert.assertEquals("Laptops & Notebooks message validation",expectedComponentsmsg,actualComponentsmsg);
    }


    @After
    public void teardown(){
        //closeBrowser();
    }
}
