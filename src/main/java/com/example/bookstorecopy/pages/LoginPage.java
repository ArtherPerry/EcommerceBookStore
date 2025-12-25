package com.example.bookstorecopy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators using @FindBy annotation
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "viewPassword")
    private WebElement viewPasswordCheckbox;

    @FindBy(css = "button.btn-primary")
    private WebElement loginButton;

    @FindBy(css = ". error-message")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[contains(text(), 'Sign up')]")
    private WebElement signUpLink;

    @FindBy(xpath = "//a[contains(text(), 'Forgot Password? ')]")
    private WebElement forgotPasswordLink;

    @FindBy(css = ".login-container h2")
    private WebElement pageHeading;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page actions
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField. sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void clickViewPasswordCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(viewPasswordCheckbox));
        viewPasswordCheckbox. click();
    }

    public String getPasswordFieldType() {
        return passwordField.getAttribute("type");
    }

    public void waitForPasswordFieldType(String expectedType) {
        wait.until(driver -> passwordField.getAttribute("type").equals(expectedType));
    }

    public String getUsernameFieldValue() {
        return usernameField.getAttribute("value");
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage. isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions. visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageHeading() {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageHeading));
            return pageHeading.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/login");
    }

    public boolean isViewPasswordCheckboxSelected() {
        return viewPasswordCheckbox.isSelected();
    }

    public boolean isUsernameFieldDisplayed() {
        try {
            return usernameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordFieldDisplayed() {
        try {
            return passwordField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignUpLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink));
        signUpLink.click();
    }

    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        forgotPasswordLink.click();
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}