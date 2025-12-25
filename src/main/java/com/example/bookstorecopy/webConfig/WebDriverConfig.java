package com.example.bookstorecopy.webConfig;

import io.github.bonigarcia. wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org. openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class WebDriverConfig {

    private static final int IMPLICIT_WAIT_SECONDS = 10;
    private static final int PAGE_LOAD_TIMEOUT_SECONDS = 30;

    public static WebDriver createChromeDriver() {
        return createChromeDriver(false);
    }

    public static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }

        WebDriver driver = new ChromeDriver(options);
        configureTimeouts(driver);
        return driver;
    }

    public static WebDriver createFirefoxDriver() {
        return createFirefoxDriver(false);
    }

    public static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        configureTimeouts(driver);
        return driver;
    }

    public static WebDriver createSafariDriver() {
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        configureTimeouts(driver);
        return driver;
    }

    private static void configureTimeouts(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT_SECONDS));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
    }

    public static WebDriver createDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "firefox":
                return createFirefoxDriver(headless);
            case "safari":
                return createSafariDriver();
            case "chrome":
            default:
                return createChromeDriver(headless);
        }
    }
}
