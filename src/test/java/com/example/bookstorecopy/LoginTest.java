package com.example.bookstorecopy;

import com.example.bookstorecopy.pages.LoginPage;
import com.example.bookstorecopy.webConfig. WebDriverConfig;
import org. junit.jupiter.api.*;
import org.openqa.selenium. WebDriver;

import static org. junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private static WebDriver driver;
    private LoginPage loginPage;
    private static final String BASE_URL = "http://localhost:8080";

    @BeforeAll
    public static void setUpClass() {
        driver = WebDriverConfig.createChromeDriver();
        System.out.println("✓ WebDriver initialized successfully");
    }

    @BeforeEach
    public void setUp() {
        driver.get(BASE_URL + "/login");
        loginPage = new LoginPage(driver);
        System.out.println("→ Navigating to:  " + BASE_URL + "/login");
    }

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
            System. out.println("✓ WebDriver closed successfully");
        }
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Login page loads successfully")
    public void testLoginPageLoads() {
        System.out.println("TEST 1: Checking if login page loads...");
        assertTrue(loginPage. isOnLoginPage(), "Should be on login page");
        assertEquals("Login", loginPage.getPageTitle(), "Page title should be 'Login'");
        System.out.println("✓ TEST 1 PASSED:  Login page loaded successfully");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Successful login with admin credentials")
    public void testSuccessfulLoginWithAdmin() {
        System.out. println("TEST 2: Testing login with admin credentials...");
        loginPage.login("admin", "admin123");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL:  " + currentUrl);
        assertTrue(currentUrl.contains("/admin/dashboard"),
                "Should redirect to admin dashboard. Current URL: " + currentUrl);
        System.out.println("✓ TEST 2 PASSED: Admin login successful");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Successful login with newadmin credentials")
    public void testSuccessfulLoginWithNewAdmin() {
        System.out.println("TEST 3: Testing login with newadmin credentials...");
        loginPage.login("newadmin", "newpassword");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/admin/dashboard"),
                "Should redirect to admin dashboard. Current URL: " + currentUrl);
        System.out.println("✓ TEST 3 PASSED:  Newadmin login successful");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Login with invalid username")
    public void testLoginWithInvalidUsername() {
        System.out.println("TEST 4: Testing login with invalid username...");
        loginPage.login("invaliduser", "admin123");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL:  " + currentUrl);
        assertTrue(currentUrl.contains("login") || currentUrl.contains("error"),
                "Should remain on login page or show error. Current URL:  " + currentUrl);
        System.out.println("✓ TEST 4 PASSED: Invalid username handled correctly");
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Login with invalid password")
    public void testLoginWithInvalidPassword() {
        System.out.println("TEST 5: Testing login with invalid password...");
        loginPage.login("admin", "wrongpassword");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("login") || currentUrl.contains("error"),
                "Should remain on login page or show error. Current URL:  " + currentUrl);
        System.out.println("✓ TEST 5 PASSED: Invalid password handled correctly");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: View password functionality")
    public void testViewPasswordToggle() {
        System.out.println("TEST 6: Testing view password toggle...");
        loginPage.enterPassword("testpassword");

        // Initially password should be hidden
        assertEquals("password", loginPage.getPasswordFieldType(),
                "Password field type should be 'password'");
        System.out.println("✓ Password initially hidden");

        // Click view password checkbox
        loginPage.clickViewPasswordCheckbox();

        // Wait for the JavaScript to change the field type
        loginPage.waitForPasswordFieldType("text");

        // Password should be visible
        assertEquals("text", loginPage.getPasswordFieldType(),
                "Password field type should be 'text' when checkbox is selected");
        assertTrue(loginPage.isViewPasswordCheckboxSelected(),
                "View password checkbox should be selected");
        System.out.println("✓ Password visible when checkbox selected");

        // Uncheck the box
        loginPage.clickViewPasswordCheckbox();

        // Wait for the JavaScript to change the field type back
        loginPage.waitForPasswordFieldType("password");

        // Password should be hidden again
        assertEquals("password", loginPage.getPasswordFieldType(),
                "Password field type should be 'password' when checkbox is unselected");
        assertFalse(loginPage.isViewPasswordCheckboxSelected(),
                "View password checkbox should not be selected");
        System.out.println("✓ TEST 6 PASSED: View password toggle works correctly");
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Login with empty credentials")
    public void testLoginWithEmptyCredentials() {
        System.out.println("TEST 7: Testing login with empty credentials...");
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        // HTML5 validation should prevent submission
        assertTrue(loginPage.isOnLoginPage(), "Should remain on login page");
        System.out.println("✓ TEST 7 PASSED: Empty credentials validation works");
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: All form elements are visible")
    public void testFormElementsVisible() {
        System.out.println("TEST 8:  Checking if all form elements are visible...");
        assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be visible");
        assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be visible");
        assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible");
        System.out.println("✓ TEST 8 PASSED:  All form elements are visible");
    }
}