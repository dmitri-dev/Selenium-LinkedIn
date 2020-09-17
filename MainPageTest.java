package org.example.selenium_linkedin;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    public static WebDriver Mydriver = new ChromeDriver();
    @Test
    public void openPage() throws InterruptedException {
        String username = "pzohir.beddiar.7@mymailcr.com";
        String password = "Password1!";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = MainPageTest.Mydriver;
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://www.linkedin.com");
        driver.findElement(By.id("session_key")).sendKeys(username);
        driver.findElement(By.id("session_password")).sendKeys(password);

        driver.findElement(By.className("sign-in-form__submit-button")).click();
//        boolean skipButtonNotExists = driver.findElements(By.className("secondary-action")).isEmpty();
//        if (!skipButtonNotExists) {
//            driver.findElement(By.className("secondary-action")).click();
//        }
        driver.findElement(By.id("mynetwork-tab-icon")).click();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 500)");
        driver.findElement(By.className("msg-overlay-bubble-header")).click();

        String firstItemId = MainPageTest.Mydriver.findElement(By.xpath("//button[@data-control-name='invite']")).getAttribute("id");
        String firstItemIdShort = firstItemId.substring(5);
        System.out.println(firstItemId + " / " + firstItemIdShort + " / "
//           + firstItemIdInt
        );

        int firstItemIdInt = Integer.parseInt(firstItemIdShort);

        getConnections(firstItemIdInt, 4);
    }

    public void getConnections(int id, int count) throws InterruptedException {
        Thread.sleep(1000);
        count--;
        WebDriver driver = MainPageTest.Mydriver;
        String idName = "ember" + String.valueOf(id);
        String tagName = driver.findElement(By.id(idName)).getTagName();
        boolean elementNotExists = driver.findElements(By.id("ember" + String.valueOf(id))).isEmpty();
        if (count > 0 || !elementNotExists && tagName.equals("button")) {
            System.out.println(id);
//            ((JavascriptExecutor) Mydriver)
//                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            try {
                driver.findElement(By.id("ember" + String.valueOf(id))).click();
                getConnections(id + 10, count);
                getConnections(id + 11, count);
            } catch (Exception e) {
                getConnections(id + 30, count);
            }

        }

    }

}
