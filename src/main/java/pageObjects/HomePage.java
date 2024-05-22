package pageObjects;

import baseClass.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BaseTestClass {

    //test1
    private By emailInput = By.id("inputEmail");
    private By passwordInput = By.id("inputPassword");
    private By loginButton = By.xpath("//button[@type='submit']");

    //test 2
    private By listGroup = By.xpath("//ul[@class='list-group']/li");
    private By secondValue = By.xpath("(//ul[@class='list-group']/li)[2]");
    private By secondBatchValue = By.xpath("(//ul[@class='list-group']/li)[2]/span");

    //test 3
    private By dropdownMenuButton = By.id("dropdownMenuButton");
    private By dropdownOptions = By.className("dropdown-item");

    //test 4
    private By enabledButton = By.xpath("//div[@id='test-4-div']//button[@class='btn btn-lg btn-primary']");

    private By disabledButton = By.xpath("//div[@id='test-4-div']//button[@class='btn btn-lg btn-secondary']");

    //test 5
    private By test5Div = By.id("test-5-div");
    private By test5Button = By.id("test5-button");
    private By successAlert = By.cssSelector(".alert-success");


    public HomePage(WebDriver driver) {
        BaseTestClass.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean areLoginFormElementsPresent() {
        return driver.findElement(emailInput).isDisplayed() &&
                driver.findElement(passwordInput).isDisplayed() &&
                driver.findElement(loginButton).isDisplayed();
    }

    public int getNumberOfListItems() {
        return driver.findElements(listGroup).size();
    }

    public String getSecondListItemText() {
        return driver.findElement(secondValue).getText().trim();
    }

    public String getSecondListItemBadgeText() {
        return driver.findElement(secondBatchValue).getText();
    }

    public String getSelectedOptionText() {
        return driver.findElement(dropdownMenuButton).getText();
    }

    public void selectOptionByText(String optionText) {
        driver.findElement(dropdownMenuButton).click();
        for (WebElement option : driver.findElements(dropdownOptions)) {
            if (option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }

    public boolean isFirstButtonEnabled() {
        return driver.findElement(enabledButton).isEnabled();
    }

    public boolean isSecondButtonEnabled() {
        return driver.findElement(disabledButton).isEnabled();
    }


    public void clickDynamicButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(test5Button));
        driver.findElement(test5Button).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successAlert).isDisplayed();
    }

    public boolean isButtonDisabled() {
        return !driver.findElement(test5Button).isEnabled();
    }


    public static String getTableCellValue(int row, int col) {
        // Locate the table element
        row = row+1;
        col = col+1;
        WebElement targetCell = driver.findElement(By.xpath("//tr[" + row + "]//td[" + col + "]"));
        // Extract the cell text
        return targetCell.getText();
    }

    public String getTableCellValuefromTable(int row, int col) {
        return getTableCellValue(row, col);
    }
}