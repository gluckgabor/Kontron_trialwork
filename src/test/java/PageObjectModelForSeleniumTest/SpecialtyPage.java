package PageObjectModelForSeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialtyPage {
    private WebDriver driver;

    // Locators for Specialty page elements
    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "/html/body/app-root/div[2]/app-specialty-list/div/div/div/button[2]")
    public WebElement addButton;

    @FindBy(xpath = "//table[@id='specialties']//button[text()='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//table[@id='specialties']//button[text()='Delete']")
    private WebElement deleteButton;

    // Constructor
    public SpecialtyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void enterSpecialtyName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void clickEditButton() {
        editButton.click();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }
}


