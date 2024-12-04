package mobiasbanca.otp;

import mobiasbanca.otp.managers.DriverManager;
import mobiasbanca.otp.managers.RandomDataManager;
import mobiasbanca.otp.pageobjects.AccountPage;
import mobiasbanca.otp.pageobjects.HomePage;
import mobiasbanca.otp.pageobjects.LoginPage;
import mobiasbanca.otp.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegisterFlowWithJunit {

    private WebDriver driver;
    private HomePage homePage;
    private RegisterPage registerPage;

    @BeforeAll
    public static void beforeAllTheTests() {
        System.out.println("This methods is run before all tests from this class");
    }

    @BeforeEach
    public void beforeEachTest() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");

        homePage = new HomePage(driver);

        homePage.navigateToRegisterPage();
    }


    @Test
    @DisplayName("User is redirected to Account page when registering with valid data")
    public void registerFlowWithValidDataRedirectsTheUserToAccountPage() throws InterruptedException {


        //Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        //Actions on register page
        registerPage = new RegisterPage(driver);
        registerPage.completeTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnTheToggleBar();
        registerPage.clickOnTheContinueBtn();

        boolean urlContainSuccessKeyword = driver.getCurrentUrl().contains("success");
        Assertions.assertTrue(urlContainSuccessKeyword, "The URL of the page contains the Success Keyword");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.logOutFromTheAccount();

        homePage.navigateToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.completeLoginForm(email, password);
        loginPage.clickTheLoginBtn();

    }

    @Test
    @DisplayName("The user remains on register page when registering without accepting the terms and conditions")

    public void userRemainOnRegisterPageWhenRegisteringWithoutAcceptingPrivacyRules() throws InterruptedException {

        //Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        //Actions on register page
        registerPage = new RegisterPage(driver);
        registerPage.completeTheRegisterForm(firstName, lastName, email, password);

        registerPage.clickOnTheContinueBtn();

        Assertions.assertTrue(driver.getCurrentUrl().contains("register"));


    }

    @Test
    @DisplayName("Navigate to Login Page from Register Page")
    public void navigateToLoginPageFromRegisterPage() {
        registerPage.navigateToLoginPage();
        Assertions.assertTrue(driver.getCurrentUrl().contains("login"));
    }


    @AfterEach
    public void afterEachTest() {
        DriverManager.getInstance().quiteTheDriver();
    }

    @AfterAll
    public static void afterAllTheTests() {
        System.out.println("This methods is executed after all tests");
    }

}
