package rpc1;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Test_End_Points {

	@Test
	public void basicPingTest(){
		given().when().get("/items")
			.then().statusCode(200);
	}
	
	@Test
    public void basicCreateItem() {
        Map<String,String> item = new HashMap<>();
        item.put("id", "1");
        item.put("name", "Mushroom");
        item.put("cost", "5.00");
        item.put("details", "Restores 15 HP.");
        //item.put("category", "Health.");

        given()
        .contentType("application/json")
        .body(item)
        .when().post("/items").then()
        .statusCode(200);
    }
	
	@Test
    public void basicGetItems() {
        given().when().get("/items")
            .then().statusCode(200);
    }
	
	@Test
	public void basicModifyItem() {
		Map<String,String> item = new HashMap<>();
        item.put("id", "1");
        item.put("name", "Mushroom");
        item.put("cost", "5.00");
        item.put("details", "Restores 10 HP.");
        //item.put("category", "Health.");

        given()
	        .contentType("application/json")
	        .body(item)
	        .when().put("/items").then()
	        .statusCode(200);
	}
	
	@Test
    public void basicGetItemById() {
		given().pathParam("id", 1)
	    	.when().get("/items/{id}")
	        .then().statusCode(200);
    }

	@Test
    public void basicDeleteItem() {
		given().pathParam("id", 0)
	        .when().delete("/items/{id}")
	        .then().statusCode(200);
    }

}
