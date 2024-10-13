package SeleniumTest;

import PageObjectModelForSeleniumTest.SpecialtyPage;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

@RunWith(Enclosed.class)
public class seleniumFrontEndTest {


    public static class SpecialtyTest {
        private WebDriver driver;
        private SpecialtyPage specialtyPage;

        @Before
        public void setUp() {
            //String path = "src/test/java/utilities/chromedriver.exe"; //
            //System.setProperty("webdriver.chrome.driver", path);
            //WebDriverManager.chromedriver().browserVersion("129.0.6668.101").setup();
            //WebDriverManager.chromedriver().setup();
            
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://localhost:4200/petclinic/specialties"); // Replace with actual URL

            // Initialize the Page Object
            specialtyPage = new SpecialtyPage(driver);
        }

        @Test
        public void addSpecialtyTest() {

            // Fluent Wait setup
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            wait.until(driver -> specialtyPage.addButton.isDisplayed());


            specialtyPage.clickAddButton();
            specialtyPage.enterSpecialtyName("New Specialty");
            specialtyPage.clickSaveButton();
            driver.navigate().refresh();
            // Assert if 'New Specialty' is displayed in the specialties table

            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            wait.until(driver -> specialtyPage.addButton.isDisplayed());


            boolean isSpecialtyPresent = driver.getPageSource().contains("New Specialty");
            assert isSpecialtyPresent : "New Specialty not found after refresh.";
            System.out.println("Specialty added and verified successfully.");
        }

        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
