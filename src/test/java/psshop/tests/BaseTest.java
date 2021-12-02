package psshop.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import psshop.helpers.GetUrlFromPropertiesFile;
import psshop.logs.Log;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public WebDriver setUpWebDriver() {

        // Create Property instance and get parameter: "host", "port" from resources.properties file
        GetUrlFromPropertiesFile.getProperty();

//        BasicConfigurator.configure();

        Log.info("Test is started");

        System.setProperty("webdriver.chrome.driver", "/home/user/IdeaProjects/chromedriver");
        // Create a WebDriver
        WebDriverManager.chromedriver().setup();
        if (driver == null) {

//            driver = new ChromeDriver();
            driver = new ChromeDriver();
            WebDriverManager.chromedriver().setup();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        }
        driver.manage().window().maximize();
        return driver;
    }

    @AfterClass(alwaysRun = true)
    public void closeWebDriver() {
        driver.quit();
        Log.info("Test is finished");
    }

}
