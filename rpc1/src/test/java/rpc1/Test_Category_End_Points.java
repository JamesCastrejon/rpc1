package rpc1;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Test_Category_End_Points {

	@Test
	public void basicPingTest(){
		given().when().get("/categories")
			.then().statusCode(200);
	}
	
	@Test
    public void basicCreateUser() {
        Map<String,String> user = new HashMap<>();
        user.put("id","1");
        user.put("name", "Test");

        given()
        .contentType("application/json")
        .body(user)
        .when().post("/categories").then()
        .statusCode(200);
    }
	
	@Test
    public void basicGetUsers() {
        given().when().get("/categories")
            .then().statusCode(200);
    }

	@Test
	public void basicModifyUser() {
		Map<String,String> user = new HashMap<>();
        user.put("id","1");
        user.put("name", "Testing");

        given()
	        .contentType("application/json")
	        .body(user)
	        .when().put("/categories").then()
	        .statusCode(200);
	}
	
	@Test
    public void basicGetUserById() {
		given().pathParam("id", 1)
	    	.when().get("/categories/{id}")
	        .then().statusCode(200);
    }

	@Test
    public void basicDeleteItem() {
		given().pathParam("id", 0)
	        .when().delete("/categories/{id}")
	        .then().statusCode(200);
    }

}
