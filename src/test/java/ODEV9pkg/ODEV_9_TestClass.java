package ODEV9pkg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
public class ODEV_9_TestClass {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void loginTest() {

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        boolean isProductsTitleDisplayed = driver.findElement(By.cssSelector(".title")).isDisplayed();
        Assert.assertTrue(isProductsTitleDisplayed, "Giriş işlemi başarısız!");
    }
    @Test(priority = 2)
    public void addProductToCart() {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        String cartBadgeText = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(cartBadgeText, "1", "Ürün sepete eklenemedi!");
    }

    @Test(priority = 3)
    public void checkOutProcess() {

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();


        driver.findElement(By.id("first-name")).sendKeys("Ali");
        driver.findElement(By.id("last-name")).sendKeys("Test");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        driver.findElement(By.id("continue")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        driver.findElement(By.id("finish")).click();


        boolean isCheckoutComplete = driver.findElement(By.cssSelector(".complete-header")).isDisplayed();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(isCheckoutComplete, "Satın alma işlemi başarısız!");
    }

    @AfterTest
    public void tearDown() {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver != null) {
            driver.quit();
        }
    }
}

