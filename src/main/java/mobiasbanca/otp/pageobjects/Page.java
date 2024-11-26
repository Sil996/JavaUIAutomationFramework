package mobiasbanca.otp.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//i[@class='fa-solid fa-user'])[1]")

    protected WebElement myAccountIcon;

    @FindBy(xpath = "//a[normalize-space()='Register']")

    protected WebElement registerLink;

    @FindBy(xpath = "//a[normalize-space()='Login']")

    protected WebElement loginLink;

    public void navigateToRegisterPage() {
        myAccountIcon.click();
        registerLink.click();
    }

    public void navigateToLoginPage() {
        myAccountIcon.click();
        loginLink.click();
    }

}
