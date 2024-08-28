package smokeTest;

import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import saucePOM.Sauce_loginPage;
import saucePOM.Sauce_productPage;

public class login_logoutTests {
    //.........................Defining instances....................
    SHAFT.GUI.WebDriver chromeDriver;
    SHAFT.TestData.JSON credentials;


//.............Test Cases........................

    //....................- Test the login functionality with valid credentials. ...................
    @Test
    public void loginWithValidCredentials(){
        String username =credentials.getTestData("validUser1.username");
        String password =credentials.getTestData("validUser1.password");
    new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password);
    //assetion of login successfully
    new Sauce_productPage(chromeDriver).validateProductName("Sauce Labs Onesie");
    }

    //........- Test the login functionality with invalid credentials................
    @Test
    public void loginWithINValidCredentials(){
        String username =credentials.getTestData("InvalidCredentials.username");
        String password =credentials.getTestData("InvalidCredentials.password");
        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password)
                //assertion of the error message of invalid login
                .ErrorMessageOfInvalidLogin("password do not match any user in");
    }
    @Test
    public void loginWithLockedUser(){
        String username =credentials.getTestData("lockedUser.username");
        String password =credentials.getTestData("lockedUser.password");
        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password)
                //assertion of the error message of invalid login
                .ErrorMessageOfInvalidLogin("Sorry, this user has been locked out.");
    }

    @Test
    public void userCanLogoutSuccessfully(){
        String username =credentials.getTestData("validUser1.username");
        String password =credentials.getTestData("validUser1.password");
        new Sauce_loginPage(chromeDriver).navigateToUrl().loginWithCredentials(username, password);
        //assetion of login successfully
        new Sauce_productPage(chromeDriver).validateProductName("Sauce Labs Onesie")
                .logout()
                .assertionOfLogout();
    }


    @BeforeClass(description = "Setup Test Data.")
    public void beforeClass() {
        credentials = new SHAFT.TestData.JSON("credentials.json");
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
