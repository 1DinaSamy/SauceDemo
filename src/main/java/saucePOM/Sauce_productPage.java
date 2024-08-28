package saucePOM;

import com.helger.commons.thirdparty.IThirdPartyModuleProviderSPI;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class Sauce_productPage {
    /////////////////////////// Driver Instance \\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private SHAFT.GUI.WebDriver chromeDriver;
    WebDriver driver = new ChromeDriver();


    ////////////////////////////// Variables \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private String url = "https://www.saucedemo.com/" ;
    /////////////////////////////// Locators \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


 // List<WebElement> productsList = chromeDriver.getDriver().findElements(By.className("inventory_item"));
   // List<WebElement> productsList = chromeDriver.findElements(By.className("inventory_item"));
    private final By prooductSName = By.id("item_2_title_link");
    private final By produc2tName = By.xpath("//*[@id=\"item_4_title_link\"]");
    private final By prooductSPrice = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div");
    private final By prooduct2Price = By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[2]/div[2]/div");
    private final By productSDescription = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/div");
    private final By product2Description = By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[2]/div[1]/div");
    private final By filterButton = By.className("select_container");
    private final By sortFromLowToHigh = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]");
    private final By addToCardButton1 = By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    private final By addToCardButton2 = By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
    private final By RemoveFromCardButton = By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]");
    private final By shoppingBadge = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logout = By.id("logout_sidebar_link");

    ///////////////////////////// Constructor \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public Sauce_productPage(SHAFT.GUI.WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /////////////////////////////// Actions \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
/* @Step("Verify that all products are displayed correctly. ")
    public Sauce_productPage verifyTheProductListIsDisplayed() {
        for (WebElement product : productsList) {
            // Verify product is displayed
            Assert.assertTrue(product.isDisplayed(), "ProductList is displayed");
        }
        return this;
    }*/

    @Step("Verify The product name")
    public Sauce_productPage validateProductName (String expectedResult) {
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(prooductSName)).contains(expectedResult).perform();
        return this;
    }
    @Step("Verify The product price")
    public Sauce_productPage validateProductPrice (String expectedResult) {
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(prooductSPrice)).contains(expectedResult).perform();
        return this;
    }
    @Step("Verify The product description")
    public Sauce_productPage validateProductdescription (String expectedResult) {
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(productSDescription)).contains(expectedResult).perform();
        return this;
    }
    @Step("Sort By price from low to high")
    public Sauce_productPage sortByPriceFromLowToHigh () {
/*
        // Locate product price elements
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".product-price"));
        // Adjust selector as needed

        // Extract prices into a list and convert them to numerical values
        List<Double> productPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "").replace(",", "").trim(); // Adjust based on currency format
            productPrices.add(Double.parseDouble(priceText));
        }

        // Create a copy of the list and sort it
        List<Double> sortedProductPrices = new ArrayList<>(productPrices);
        Collections.sort(sortedProductPrices); // Sort in ascending order

        // Validate the sorting*/

        chromeDriver
                .element().click(filterButton)
                .element().click(sortFromLowToHigh);
        return this;
    }
    @Step("Verify The product2 name")
    public Sauce_productPage validateProduct2Name (String expectedResult) {
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(produc2tName)).contains(expectedResult).perform();
        return this;
    }
    @Step("Verify The product2 price")
    public Sauce_productPage validateProduct2Price (String expectedResult) {
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(prooduct2Price)).contains(expectedResult).perform();
        return this;
    }
    @Step("Verify The product2 description")
    public Sauce_productPage validateProduct2description (String expectedResult) {
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(product2Description)).contains(expectedResult).perform();
        return this;
    }

    @Step("Add a product to a card")
    public Sauce_productPage addAProductToAcard() throws InterruptedException {
        chromeDriver.element().click(addToCardButton1)
                        .element().click(addToCardButton2);
        Thread.sleep(3000);
        return this;
    }
    @Step("Remove a product to a card")
    public Sauce_productPage removeaddAProductToAcard(){
        chromeDriver.element().click(RemoveFromCardButton);
        return this;
    }
    @Step("Verify The number of added products")
    public Sauce_productPage verifyNoOfProducts(String expectedResult){
        SHAFT.Validations.verifyThat().object(chromeDriver.element().getText(shoppingBadge)).contains(expectedResult).perform();
        return this;
    }

    @Step("The User Can Logout")
    public Sauce_loginPage logout(){
        chromeDriver.element().click(burgerMenu)
                .element().click(logout);
        return new Sauce_loginPage(chromeDriver);
    }
}
