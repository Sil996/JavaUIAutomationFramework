package mobiasbanca.otp;

import mobiasbanca.otp.managers.DriverManager;
import mobiasbanca.otp.managers.RandomDataManager;
import mobiasbanca.otp.pageobjects.AccountPage;
import mobiasbanca.otp.pageobjects.HomePage;
import mobiasbanca.otp.pageobjects.LoginPage;
import mobiasbanca.otp.pageobjects.RegisterPage;
import org.openqa.selenium.WebDriver;

public class TestRunnerWithPageObjects {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");

        HomePage homePage = new HomePage(driver);

        homePage.navigateToRegisterPage();


        //Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        //Actions on register page
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.completeTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnTheToggleBar();
        registerPage.clickOnTheContinueBtn();

        Thread.sleep(5000);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.logOutFromTheAccount();

        homePage.navigateToLoginPage();

        Thread.sleep(5000);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.completeLoginForm(email, password);
        loginPage.clickTheLoginBtn();

        Thread.sleep(5000);


        driver.quit();


    }
}
