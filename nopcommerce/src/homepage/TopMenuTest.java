package homepage;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TopMenuTest extends BaseTest {

    String baseurl="https://demo.nopcommerce.com/";

    @Before
    public void Setup(){
        openBrowser(baseurl);
    }

    public void selectMenu(By menu){
        driver.findElement(menu).click();
    }

    @Test
    public void verifyPageNavigation(){
        selectMenu(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Computers ']"));
        selectMenu(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Electronics ']"));
        selectMenu(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Apparel ']"));
        selectMenu(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Digital downloads ']"));
        selectMenu(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Books ']"));
        selectMenu(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Jewelry ']"));
        selectMenu(By.xpath("//body/div[6]/div[2]/ul[1]/li/a[text()='Gift Cards ']"));
    }

    @After
    public void teardown(){
        //closeBrowser();
    }
}
