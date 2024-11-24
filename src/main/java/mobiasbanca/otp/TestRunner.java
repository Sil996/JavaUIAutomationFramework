package mobiasbanca.otp;

import mobiasbanca.otp.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://www.google.md/");

        String theNameOfTheFirstTab = driver.getWindowHandle();

        //Open a new window and navigate to mobile.de page
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.mobile.de/");
        Thread.sleep(2000);

        //Close the current tab
        driver.close();

        driver.switchTo().window(theNameOfTheFirstTab);
        driver.get("https://www.google.md/");
        Thread.sleep(2000);
        driver.quit();
    }
}