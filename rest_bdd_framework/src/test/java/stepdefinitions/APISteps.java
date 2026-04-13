package stepdefinitions;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utils.DataReader;
import utils.PayloadModifier;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class APISteps {

    ScenarioContext context = new ScenarioContext();
    Response response;

    @Given("User prepares payload from {string}")
    public void preparePayload(String sheet) throws Exception {
        Map<String, String> data = DataReader.getApiTestData(sheet);
        String payload = PayloadModifier.updatePayload("sample.json", data);
        context.set("payload", payload);
    }

    @Then("User hits API")
    public void hitApi() {
        String payload = (String) context.get("payload");

        response = given()
                .baseUri("https://postman-echo.com")
                .body(payload)
                .post("/post");

        response.then().statusCode(200);
    }
}
