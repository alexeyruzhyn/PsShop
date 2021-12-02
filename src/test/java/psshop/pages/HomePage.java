package psshop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(xpath = "(//input[@class='search-form__input'])[2]")
    private WebElement searchField;

    @FindBy(xpath = "(//button[@class='btn btn--search search-form__btn'])[2]")
    private WebElement buttonFind;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void moveToTheSearchFieldEnterTheName(String name) {
        WebElement element = waitElementClickableMethod(searchField);
        element.sendKeys(name);
    }

    public void clickTheButtonFind() {
        buttonFind.click();
    }


}
