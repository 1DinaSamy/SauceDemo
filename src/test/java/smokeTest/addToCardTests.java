package smokeTest;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucePOM.Sauce_loginPage;
import saucePOM.Sauce_productPage;

public class addToCardTests {
    //.........................Defining instances....................
    SHAFT.GUI.WebDriver chromeDriver;
    SHAFT.TestData.JSON credentials;
    SHAFT.TestData.JSON productData;


    //.............Test Cases........................

    //.....- Add a product to the shopping cart and verify that the cart is updated. ........
    @Test
    public void addToCardAndAssertThat() throws InterruptedException {
        String username =credentials.getTestData("validUser1.username");
        String password =credentials.getTestData("validUser1.password");

        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password);
        new Sauce_productPage(chromeDriver)
                .addAProductToAcard()
                .verifyNoOfProducts("2");
    }

    //......- Remove a product from the cart and ensure the cart is updated correctly. .........
    @Test
    public void RemoveFromCardAndAssertThat() throws InterruptedException {
        String username =credentials.getTestData("validUser1.username");
        String password =credentials.getTestData("validUser1.password");

        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password);
        new Sauce_productPage(chromeDriver)
                .addAProductToAcard()
                .verifyNoOfProducts("2")
                .removeaddAProductToAcard()
                .verifyNoOfProducts("1");
    }

    @BeforeClass(description = "Setup Test Data.")
    public void beforeClass() {
        credentials = new SHAFT.TestData.JSON("credentials.json");
        productData = new SHAFT.TestData.JSON("productData.json");
    }
    @BeforeMethod(description = "Setup Browser instance.")
    public void beforeMethod() {
        chromeDriver = new SHAFT.GUI.WebDriver();
    }
    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        chromeDriver.quit();
        SHAFT.Properties.reporting.set().openAllureReportAfterExecution(true);
    }
}
