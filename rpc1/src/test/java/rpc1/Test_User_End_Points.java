package rpc1;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Test_User_End_Points {

	@Test
	public void basicUserPingTest(){
		given().when().get("/users")
			.then().statusCode(200);
	}
	
	@Test
    public void basicCreateUser() {
        Map<String,String> user = new HashMap<>();
        user.put("userId","1");
        user.put("userName", "Test");
        user.put("password", "Test");

        given()
        .contentType("application/json")
        .body(user)
        .when().post("/users").then()
        .statusCode(200);
    }
	
	@Test
    public void basicGetUsers() {
        given().when().get("/users")
            .then().statusCode(200);
    }

	
	@Test
	public void basicModifyUser() {
		Map<String,String> user = new HashMap<>();
        user.put("userId","1");
        user.put("userName", "Testing");
        user.put("password", "Testing");

        given()
	        .contentType("application/json")
	        .body(user)
	        .when().put("/users").then()
	        .statusCode(200);
	}
	
	@Test
    public void basicGetUserById() {
		given().pathParam("id", 1)
	    	.when().get("/users/{id}")
	        .then().statusCode(200);
    }

	@Test
    public void basicDeleteItem() {
		given().pathParam("id", 0)
	        .when().delete("/users/{id}")
	        .then().statusCode(200);
    }

}
