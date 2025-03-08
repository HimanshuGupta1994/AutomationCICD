package dheerajParmar.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import dheerajParmar.TestComponents.BaseTest;
import dheerajParmar.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException {
        landingPage.loginApplication("dheerajparmar014@gmail.com", "Dheeraj@00");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

    public static void main(String[] arg) {
        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.amazon.in/");
        Actions action = new Actions(driver);
//        action.moveToElement(driver.findElement(By.xpath("//*[@class='nav-line-2 ']"))).build().perform();
//        driver.findElement(By.xpath("//*[@id='nav-al-signin']/div/a/span")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//        System.out.println(driver.findElement(By.xpath("//h1[contains(text(), 'Sign')]")).getText());
//        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='a-spacing-small']")).getText(), "Sign in or create account");
//    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobile");
//       List<WebElement> list = driver.findElements(By.xpath("//*[@class='left-pane-results-container']/div/div/div"));
//       for(int i =0; i<list.size(); i++)
//           System.out.println(list.get(i).getText());
        driver.get("https://www.flipkart.com/");
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Fashion']"))).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        action.moveToElement(driver.findElement(By.xpath("//a[text()='Men Footwear']"))).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Slippers & Flip Flops')]"))).click().build().perform();
    }
}
