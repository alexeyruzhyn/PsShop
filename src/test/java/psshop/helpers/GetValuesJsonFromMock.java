package psshop.helpers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.util.ArrayList;
import java.util.List;

public class GetValuesJsonFromMock {

    public List<Object> objectsWithGoods;

    public List<Object> getThingsFromApi(String host, String port) {

        String URL = String.format("http://%s:%s/%s", host, port, "%s");

        String apiURL = String.format(URL, "api/goods");

        // Use a RestAssured library and get array with Json objects from Mock Api
        RequestSpecification spec = RestAssured.given();

        spec.get(apiURL).then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();


        String getBodyFromMockResponse = spec.get(apiURL).then().extract().body().asString();

        Object obj = JSONValue.parse(getBodyFromMockResponse);

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray arrayWithGoodsFromMockResponse = (JSONArray) jsonObject.get("goods");

        objectsWithGoods = new ArrayList<>();

        //Checking whether the JSON array has some value or not
        if (arrayWithGoodsFromMockResponse != null) {

            //Adding each element of JSON array into ArrayList
            objectsWithGoods.addAll(arrayWithGoodsFromMockResponse);
        }

        return objectsWithGoods;

    }
}
