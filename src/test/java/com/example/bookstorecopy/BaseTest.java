package com.example.bookstorecopy;
import com.example.bookstorecopy.webConfig.WebDriverConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org. junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa. selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance. Lifecycle.PER_CLASS)
public abstract class BaseTest {

    @LocalServerPort
    protected int port;

    protected WebDriver driver;
    protected String baseUrl;

    @BeforeAll
    public void setUpBase() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverConfig.createDriver(browser, headless);
        baseUrl = "http://localhost:" + port;
    }

    @AfterEach
    public void tearDownBase() {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public void tearDownClassBase() {
        if (driver != null) {
            driver. quit();
        }
    }
}