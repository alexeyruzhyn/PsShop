package psshop.tests;

import net.minidev.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import psshop.helpers.GetUrlFromPropertiesFile;
import psshop.helpers.GetValuesJsonFromMock;
import psshop.logs.Log;
import psshop.pages.HomePage;
import psshop.pages.PageWithResults;

import java.util.List;


public class CompareThingsApiAndUITest extends BaseTest {

    String URL;

    GetValuesJsonFromMock getValuesJsonFromMock = new GetValuesJsonFromMock();

    @BeforeTest
    public void setUpTest() {

        // Create Property instance and get parameter: "Site" from resources.properties file
        GetUrlFromPropertiesFile.getProperty();
        URL = GetUrlFromPropertiesFile.prop.getProperty("site");
    }

    String nameOfVideoCardFromApi;
    String nameOfVideoCardFromUI;

    String priceOfVideoCardFromApi;
    String priceOfVideoCardFromUI;

    String availabilityOfVideoCardFromApi;
    String availabilityOfVideoCardFromUI;

    String codeOfVideoCardFromApi;
    String codeOfVideoCardFromUI;

    @Test
    public void getNameOfThingForSearchInUI() {

        String host = GetUrlFromPropertiesFile.prop.getProperty("host");
        String port = GetUrlFromPropertiesFile.prop.getProperty("port");

        // Create a SoftAssert instance
        SoftAssert softAssert = new SoftAssert();

        // Get the list with goods from Api
        List<Object> listWithGoods = getValuesJsonFromMock.getThingsFromApi(host, port);

        // Iterating the array with goods Json objects , create Json object for each and get the necessary parameter from each Json object
        for (Object listWithGood : listWithGoods) {

            JSONObject object = (JSONObject) listWithGood;

            nameOfVideoCardFromApi = object.getAsString("name");

            priceOfVideoCardFromApi = object.getAsString("price");

            availabilityOfVideoCardFromApi = object.getAsString("availability");

            codeOfVideoCardFromApi = object.getAsString("code");

            findFirstProduct(softAssert);

        }

        // Check the equals good from Api and UI
        softAssert.assertAll();

    }

    public void findFirstProduct(SoftAssert softAssert) {

        // Open URL
        driver.get(URL);

        // Create a HomePage instance
        HomePage homePage = new HomePage(driver);

        // Get name of goods from Api and paste it in the search field in UI site
        homePage.moveToTheSearchFieldEnterTheName(nameOfVideoCardFromApi);

        // Click the button: 'Search' in UI site
        homePage.clickTheButtonFind();

        // Create a PageWithResults instance
        PageWithResults pageWithResults = new PageWithResults(driver);

        // Get goods name from UI and equals with goods from Api
        nameOfVideoCardFromUI = pageWithResults.getNameOfThingUI();
        Log.info("Check the name from API and UI for " + nameOfVideoCardFromApi + " and " + nameOfVideoCardFromUI);
        softAssert.assertEquals(nameOfVideoCardFromApi, nameOfVideoCardFromUI);

        // Get goods price from UI and equals with goods from Api
        priceOfVideoCardFromUI = pageWithResults.getPriceOfThingUI();
        Log.info("Check the price from API and UI for " + priceOfVideoCardFromUI + " and " + priceOfVideoCardFromApi);
        softAssert.assertEquals(priceOfVideoCardFromUI, priceOfVideoCardFromApi);

        // Get goods code from UI and equals with goods from Api
        codeOfVideoCardFromUI = pageWithResults.getCodeOfThingOfThingUI();
        Log.info("Check the code from API and UI for " + codeOfVideoCardFromUI + " and " + codeOfVideoCardFromApi);
        softAssert.assertEquals(codeOfVideoCardFromUI.replace(" ", ""),
                codeOfVideoCardFromApi.replace(" ", ""));

        // Get goods availability from UI and equals with goods from Api
        availabilityOfVideoCardFromUI = pageWithResults.getAvailabilityOfThingOfThingUI();
        Log.info("Check the availability fro API and UI for " + availabilityOfVideoCardFromUI + " and " + availabilityOfVideoCardFromApi);
        softAssert.assertEquals(availabilityOfVideoCardFromUI, availabilityOfVideoCardFromApi);

    }

}
