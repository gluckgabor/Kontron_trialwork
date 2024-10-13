package SeleniumTest;

import PageObjectModelForSeleniumTest.SpecialtyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class seleniumFrontEndTest {


    public class SpecialtyTest {
        private WebDriver driver;
        private SpecialtyPage specialtyPage;

        @BeforeMethod
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://localhost:4200/petclinic/specialties"); // Replace with actual URL

            // Initialize the Page Object
            specialtyPage = new SpecialtyPage(driver);
        }

        @Test
        public void addSpecialtyTest() {
            specialtyPage.clickAddButton();
            specialtyPage.enterSpecialtyName("New Specialty");
            specialtyPage.clickSaveButton();
            driver.navigate().refresh();
            // Assert if 'New Specialty' is displayed in the specialties table
            boolean isSpecialtyPresent = driver.getPageSource().contains("New Specialty");
            assert isSpecialtyPresent : "New Specialty not found after refresh.";
            System.out.println("Specialty added and verified successfully.");
        }

        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
