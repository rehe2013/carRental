import com.fasterxml.jackson.databind.ObjectMapper;
import config.TestConfig;
import config.EndPoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

public class CarRental extends TestConfig {

    /* Question 1: Print all the blue Teslas received in the web service response. Also print the notes */
    @Test
    public void getBlueTeslas(){
        Response response = get(EndPoints.RENTALCAR);
        String color = "Blue";
        String make = "Teslas";

        ArrayList<Map<String, ?>> carList = response.path
          ("Cars.findAll{it.make == '%s' && it.metadata.Color == '%s'}", make, color);
        for (Map<String, ?> car: carList ) {
            System.out.println(car.toString());
            Assert.assertEquals(make, car.get("make"));
            ObjectMapper objMapper = new ObjectMapper();
            Object obj = car.get("metadata");
            Map<String, String> metadataMap = objMapper.convertValue(obj, Map.class);
            Assert.assertEquals(color, metadataMap.get("Color"));
            System.out.println(metadataMap.get("Notes"));
        }
    }

    /* Question 2: Return all cars which have the lowest per day rental cost for both cases: a. Price only */
    @Test
    public void getLowestPriceCar(){
        Response response = get(EndPoints.RENTALCAR);
        Map<String, ?> carMap = response.path
           ("Cars.min {it.perdayrent.Price}");
        System.out.println(carMap.toString());
        ObjectMapper objMapper = new ObjectMapper();
        Object obj = carMap.get("perdayrent");
        Map<String, Integer> perdayrentMap =  objMapper.convertValue(obj, Map.class);
        System.out.println("The lowest price is $" + perdayrentMap.get("Price"));
    }

    @Test
    public void getHighestDiscountCar(){
        Response response = get(EndPoints.RENTALCAR);
        Map<String, ?> carMap = response.path
                ("Cars.max {it.perdayrent.Discount}");
        System.out.println(carMap.toString());
    }

    /* Question 2: Return all cars which have the lowest per day rental cost for both cases: b. Price after discount */
    @Test
    public void getLowestPriceAfterDiscountCar() {
        Response response = get(EndPoints.RENTALCAR);
        Map<String, ?> lowestPriceCar = response.path
                ("Cars.min{ (int)it.perdayrent.Price -  (int)it.perdayrent.Discount}");
        System.out.println(lowestPriceCar.toString());
        ObjectMapper objMapper = new ObjectMapper();
        Object obj = lowestPriceCar.get("perdayrent");
        Map<String, Integer> perdayrentMap =  objMapper.convertValue(obj, Map.class);
        Integer afterDiscount =  perdayrentMap.get("Price") - perdayrentMap.get("Discount");
        System.out.println("The lowest price is $" + afterDiscount);
    }


    /* Question 3: Find the highest revenue generating car. year over year maintenance cost + depreciation is the total
                   expense per car for the full year for the rental car company.
    			   The objective is to find those cars that produced the highest profit in the last year */
    @Test
    public void getHighestRevenueCare(){
        Response response = get(EndPoints.RENTALCAR);
        Map<String, ?> carMap = response.path
                ("Cars.max {((int)it.perdayrent.Price - (int)it.perdayrent.Discount)" +
                " * (int)it.metrics.rentalcount.yeartodate - (int)it.metrics.yoymaintenancecost - " +
                "(int)it.metrics.depreciation }");
        System.out.println(carMap.toString());
    }
}
