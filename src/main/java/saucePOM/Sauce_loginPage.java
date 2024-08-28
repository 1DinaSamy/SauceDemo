package saucePOM;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Sauce_loginPage
{
    /////////////////////////// Driver Instance \\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private SHAFT.GUI.WebDriver chromeDriver;
    ////////////////////////////// Variables \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private String url = "https://www.saucedemo.com/" ;
    /////////////////////////////// Locators \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private final By userNameTxtField = By.id("user-name");
    private final By passwordTxtxField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.className("error-message-container");

    ///////////////////////////// Constructor \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public Sauce_loginPage(SHAFT.GUI.WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /////////////////////////////// Actions \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    @Step("Navigate to the Login Page.")
    public Sauce_loginPage navigateToUrl() {
        chromeDriver.browser().navigateToURL(url);
        return this;
    }
    @Step("Enter your credentials then click on the Login Button")
    public Sauce_loginPage loginWithCredentials (String username, String password) {
        chromeDriver.element().type(userNameTxtField,username)
                .type(passwordTxtxField, password)
                .click(loginButton);
        return new Sauce_loginPage(chromeDriver);
    }

    @Step("Enter your credentials then click on the Login Button")
    public Sauce_loginPage ErrorMessageOfInvalidLogin (String expectedResult) {
        /*WebDriverWait wait = new WebDriverWait((WebDriver) chromeDriver, Duration.ofSeconds(10));
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));*/
        //SHAFT.Properties.timeouts.waitUntilTimeout();
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(errorMessage)).contains(expectedResult).perform();
        return new Sauce_loginPage(chromeDriver);
    }
    @Step
    public Sauce_loginPage assertionOfLogout(){
        SHAFT.Validations.verifyThat()
                .object(chromeDriver.element().isElementDisplayed(loginButton))
                .isEqualTo(true)
                .perform();
        return this;
    }
}
