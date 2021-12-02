package psshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageWithResults extends BasePage {

    @FindBy(xpath = "//span[@class='product-thumb__name']")
    private WebElement nameOfThing;

    @FindBy(xpath = "//span[@class='product-thumb__price']")
    private WebElement priceOfThing;

    @FindBy(xpath = "//span[@class='product-thumb__id']")
    private WebElement codeOfThing;

    @FindBy(xpath = "//span[@class='product-thumb__availability product-thumb__availability--available']")
    private WebElement availabilityOfThing;

    public PageWithResults(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getNameOfThingUI() {
        waitElementClickableMethod(nameOfThing);
        return nameOfThing.getText();
    }

    public String getPriceOfThingUI() {
        waitElementClickableMethod(priceOfThing);
        return priceOfThing.getText();
    }

    public String getCodeOfThingOfThingUI() {
        waitElementClickableMethod(codeOfThing);
        return codeOfThing.getText();
    }

    public String getAvailabilityOfThingOfThingUI() {
        waitElementClickableMethod(availabilityOfThing);
        return availabilityOfThing.getText();
    }

}
