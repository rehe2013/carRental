package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class TestConfig {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup() {
        requestSpec =new RequestSpecBuilder().
            setBaseUri("http://localhost").
            setPort(8000).
            addHeader("Content-Type","application/json").
            addHeader("Accept","application/json").
            build();
        RestAssured.requestSpecification = requestSpec;

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).build();
        RestAssured.responseSpecification = responseSpec;
    }
}
