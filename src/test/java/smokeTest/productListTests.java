package smokeTest;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucePOM.Sauce_loginPage;
import saucePOM.Sauce_productPage;

public class productListTests {
    //.........................Defining instances....................
    SHAFT.GUI.WebDriver chromeDriver;
    SHAFT.TestData.JSON credentials;
    SHAFT.TestData.JSON productData;


    //.............Test Cases........................

    //.........- Verify that all products are displayed correctly. ...........
 /*@Test
    public void verifyThatTheProductListIsDisplayed(){
        String username =credentials.getTestData("validUser1.username");
        String password =credentials.getTestData("validUser1.password");
        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password);
        new Sauce_productPage(chromeDriver)
                .verifyTheProductListIsDisplayed();
    }
*/
    //.........- Validate the product details (name, price, and description). ...........
    @Test
    public void verifyTheProductData(){
        String username =credentials.getTestData("validUser1.username");
        String password =credentials.getTestData("validUser1.password");

        String ProductName =productData.getTestData("product2.name");
        String PoductPrice =productData.getTestData("product2.price");
        String ProductDisc =productData.getTestData("product2.disc");

        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password);
        new Sauce_productPage(chromeDriver)
                .validateProductName(ProductName )
                .validateProductPrice(PoductPrice)
                .validateProductdescription(ProductDisc);
    }

    //.........- Test the sorting functionality to ensure products are sorted correctly by price. ...........
    @Test
    public void sortTheProductsbyPriceFromLowTooHigh(){
        String username =credentials.getTestData("validUser1.username");
        String password =credentials.getTestData("validUser1.password");

        String ProductName =productData.getTestData("product1.name");
        String PoductPrice =productData.getTestData("product1.price");
        String ProductDisc =productData.getTestData("product1.disc");

        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password);
        new Sauce_productPage(chromeDriver)
                .sortByPriceFromLowToHigh()
                .validateProductName(ProductName)
                .validateProductPrice(PoductPrice)
                .validateProductdescription(ProductDisc);
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
