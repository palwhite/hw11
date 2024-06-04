package Electronics;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

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

        WebElement elementtoscrollto= driver.findElement(By.xpath("//div/h2/a[text()='Nokia Lumia 1020']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementtoscrollto);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/h2/a[text()='Nokia Lumia 1020']")));
        driver.findElement(By.xpath("//div/h2/a[text()='Nokia Lumia 1020']")).click();
        
        String exptitletxt=driver.findElement(By.xpath("//div/h1")).getText();
        String acttitletxt="Nokia Lumia 1020";
        Assert.assertEquals("Nokia Lumia 1020 validation",exptitletxt,acttitletxt);

        String expectedpricetxt=driver.findElement(By.cssSelector("span#price-value-20")).getText();
        String actualpricetxt="$349.00";
        Assert.assertEquals("Price Validation",expectedpricetxt,actualpricetxt);

        driver.findElement(By.cssSelector("input#product_enteredQuantity_20")).clear();
        driver.findElement(By.cssSelector("input#product_enteredQuantity_20")).sendKeys("2");

        driver.findElement(By.cssSelector("button#add-to-cart-button-20")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("span.close")).click();

        actions.moveToElement(driver.findElement(By.cssSelector("span.cart-label"))).build().perform();
        WebElement button=driver.findElement(By.xpath("//button[text()='Go to cart']"));
        wait.until(ExpectedConditions.visibilityOf(button));
        button.click();

        String expectedshoppingcarttxt=driver.findElement(By.xpath("//div/h1")).getText();
        String actualshoppingcarttxt="Shopping cart";
        Assert.assertEquals("Shopping cart validation",expectedshoppingcarttxt,actualshoppingcarttxt);

        String excualquantitytxt=driver.findElement(By.xpath("//div/input[@class='qty-input']")).getText();
//        String actualquantitytxt="2";
//        Assert.assertEquals("Quantity Validation",excualquantitytxt,actualquantitytxt);

        String expectedtotaltxt= driver.findElement(By.cssSelector("span.product-subtotal")).getText();
        String actualtotaltxt="$698.00";
        Assert.assertEquals("Total Validation",expectedtotaltxt,actualtotaltxt);

        driver.findElement(By.cssSelector("input#termsofservice")).click();
        driver.findElement(By.id("checkout")).click();

        String expectedwelcometxt=driver.findElement(By.xpath("//div/h1")).getText();
        String actualwelcometxt="Welcome, Please Sign In!";
        Assert.assertEquals("Welcome, Please Sign In! Validation",expectedwelcometxt,actualwelcometxt);

        //register();
        login();

        driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button[text()='Continue']")).click();

        driver.findElement(By.id("shippingoption_1")).click();

        driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/button")).click();
        driver.findElement(By.xpath("//input[@id='paymentmethod_1']")).click();

        driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']/button")).click();

        WebElement paymentlist= driver.findElement(By.id("CreditCardType"));
        paymentlist.click();
        Select select=new Select(paymentlist);
        select.selectByValue("visa");

        driver.findElement(By.id("CardholderName")).sendKeys("Nirali Patel");
        driver.findElement(By.id("CardNumber")).sendKeys("4000000000001000");

        WebElement expiremonth=driver.findElement(By.id("ExpireMonth"));
        expiremonth.click();
        Select s=new Select(expiremonth);
        s.selectByVisibleText("09");

        WebElement expireyear= driver.findElement(By.id("ExpireYear"));
        expireyear.click();
        Select select1=new Select(expireyear);
        select1.selectByValue("2026");

        driver.findElement(By.id("CardCode")).sendKeys("145");
        driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']/button")).click();

        String expectedpaymentmethod=driver.findElement(By.xpath("//li[@class='payment-method']")).getText();
        String actualpaymentmethd="Payment Method: Credit Card";
        Assert.assertEquals("payment method validation",expectedpaymentmethod,actualpaymentmethd);

        String expectedshippingmethod=driver.findElement(By.xpath("//li[@class='shipping-method']")).getText();
        String actualshippingmethd="Shipping Method: Next Day Air";
        Assert.assertEquals("Shipping method validation",expectedshippingmethod,actualshippingmethd);

        String expectedtotal=driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText();
//        String actualtotal="$698.00";
//        Assert.assertEquals("Total Validation",expectedtotal,actualtotal);

        driver.findElement(By.xpath("//div[@id='confirm-order-buttons-container']/button")).click();

        String expectedthankyoutext=driver.findElement(By.xpath("//div/h1[text()='Thank you']")).getText();
        String actualthankyoutext="Thank you";
        Assert.assertEquals("Thank you Validation",expectedthankyoutext,actualthankyoutext);

        String expectedordertxt= driver.findElement(By.xpath("//div/strong[contains(text(),'order')]")).getText();
        String actualordertxt="Your order has been successfully processed!";
        Assert.assertEquals("Your order has been successfully processed! Validation",expectedordertxt,actualordertxt);

        driver.findElement(By.xpath("//div[@class='buttons']/button")).click();

        String expectedwelcometoyourstoretxt=driver.findElement(By.xpath("//div[@class='topic-block-title']/h2")).getText();
        String actualwelcometoyourstoretxt="Welcome to our store";
        Assert.assertEquals("Welcome to our store validation",expectedwelcometoyourstoretxt,actualwelcometoyourstoretxt);

        driver.findElement(By.xpath("//div/div/ul/li/a[text()='Log out']")).click();

        String url=driver.getCurrentUrl();
        String ul="https://demo.nopcommerce.com/";
        Assert.assertEquals("url validation",url,ul);

    }

    public void register(){
        driver.findElement(By.xpath("//button[text()='Register']")).click();

        driver.findElement(By.id("gender-female")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Nirali");
        driver.findElement(By.id("LastName")).sendKeys("Patel");
        driver.findElement(By.xpath("//option[text()='30']")).click();
        driver.findElement(By.xpath("//option[text()='June']")).click();
        driver.findElement(By.xpath("//option[text()='1995']")).click();
        driver.findElement(By.id("Email")).sendKeys("12344@gmail.com");
        driver.findElement(By.id("Company")).sendKeys("CodeBuster");
        driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("123456");
        driver.findElement(By.id("register-button")).click();

        String actualresultmessgae=driver.findElement(By.className("result")).getText();
        String expectedresultmessgae="Your registration completed";
        Assert.assertEquals("Your registration completed validation",expectedresultmessgae,actualresultmessgae);

        driver.findElement(By.xpath("//div/a[text()='Continue']")).click();

        driver.findElement(By.cssSelector("input#termsofservice")).click();
        driver.findElement(By.id("checkout")).click();

        WebElement countrylist=driver.findElement(By.id("BillingNewAddress_CountryId"));
        countrylist.click();
        Select select=new Select(countrylist);
        select.selectByValue("233");

        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("London");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("123 street");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("LA3 5BD");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("123456789");
        driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button[text()='Continue']")).click();
        driver.findElement(By.id("shippingoption_1")).click();
    }

    public void login(){
        driver.findElement(By.xpath("//input[@class='email']")).sendKeys("12344@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("123456");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        driver.findElement(By.cssSelector("input#termsofservice")).click();
        driver.findElement(By.id("checkout")).click();


//        WebElement countrylist=driver.findElement(By.id("BillingNewAddress_CountryId"));
//        countrylist.click();
//        Select select=new Select(countrylist);
//        select.selectByValue("233");
//
//        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("London");
//        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("123 street");
//        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("LA3 5BD");
//        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("123456789");
//        driver.findElement(By.xpath("//div[@id='billing-buttons-container']/button[text()='Continue']")).click();
//        driver.findElement(By.id("shippingoption_1")).click();
    }

    @After
    public void teardown(){
      //  closeBrowser();
    }

}
