package testCases;

import baseClass.BaseTestClass;
import org.testng.annotations.*;
import pageObjects.HomePage;

import static org.testng.Assert.*;

public class TestAssignment extends BaseTestClass {

    public HomePage homePage = new HomePage(driver);

    @BeforeClass
    public void initBrowser(){
        initializeBrowser();
    }

    @Test(priority = 1)
    public void testLogin() {
        assertTrue(homePage.areLoginFormElementsPresent(), "Login form elements not found!");

        homePage.enterEmail("test@example.com");
        homePage.enterPassword("password");
        homePage.clickLoginButton();
    }

    @Test(priority = 2)
    public void testListItems() {
        int expectedNumItems = 3;
        int actualNumItems = homePage.getNumberOfListItems();
        assertEquals(actualNumItems, expectedNumItems, "Number of List Items does not match");

        String expectedSecondItemText = "List Item 2";
        String actualSecondItemText = homePage.getSecondListItemText();
        assertTrue(actualSecondItemText.contains(expectedSecondItemText), "Second List Item Text is incorrect");

        String expectedSecondItemBadgeText = "6";
        String actualSecondItemBadgeText = homePage.getSecondListItemBadgeText();
        assertEquals(actualSecondItemBadgeText, expectedSecondItemBadgeText, "Second List Item Badge Text is incorrect");
    }

    @Test(priority = 3)
    public void testDropdown() {

        String expectedSelectedOption = "Option 1";
        String actualSelectedOption = homePage.getSelectedOptionText();
        assertEquals(actualSelectedOption, expectedSelectedOption, "Incorrect default selected option");

        homePage.selectOptionByText("Option 3");
        expectedSelectedOption = "Option 3";
        actualSelectedOption = homePage.getSelectedOptionText();
        assertEquals(actualSelectedOption, expectedSelectedOption, "Option 3 not selected");
    }

    @Test(priority = 4)
    public void testButtonStates() {

        assertTrue(homePage.isFirstButtonEnabled(), "First button is not enabled");
        assertFalse(homePage.isSecondButtonEnabled(), "Second button is unexpectedly enabled");
    }

    @Test(priority = 5)
    public void testDynamicButton() {

        homePage.clickDynamicButton();
        assertTrue(homePage.isSuccessMessageDisplayed(), "Success message not displayed");
        assertTrue(homePage.isButtonDisabled(), "Button is still enabled after click");
    }

    @Test(priority = 6)
    public void testTableCellValue() {

        int row = 2;
        int col = 2;
        String expectedCellValue = "Ventosanzap";
        String actualCellValue = homePage.getTableCellValuefromTable(row, col);
        assertEquals(actualCellValue, expectedCellValue, "Cell value (" + row + ", " + col + ") does not match");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
