package rpc1;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_User_End_Points {

	@Test
	public void AbasicUserPingTest(){
		given().when().get("/users")
			.then().statusCode(200);
	}
	
	@Test
    public void BbasicCreateUser() {
        Map<String,String> user = new HashMap<>();
        user.put("userId","1");
        user.put("userName", "Test");
        user.put("password", "Test");

        Response r = given()
        .contentType("application/json")
        .body(user)
        .when().post("/users").then()
        .statusCode(200)
        .and()
        .extract().response();

        JsonPath test = r.jsonPath();

        assertEquals("Test", test.getString("userName"));
        assertEquals("Test", test.getString("password"));
        System.out.println("Create User");
        System.out.println("UserName: Test == " + test.getString("userName"));
        System.out.println("Password: Test == " + test.getString("password"));
    }
	
	@Test
    public void CbasicGetUsers() {
        Response r = given().when().get("/users")
            .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals(29, test.getInt("[0].id"));
        System.out.println("Get User list");
        System.out.println("ID: 29 == " + test.getInt("[0].id"));
    }

	
	@Test
	public void DbasicModifyUser() {
		Map<String,String> user = new HashMap<>();
        user.put("id","29");
        user.put("userName", "Testing");
        user.put("password", "Testing");

       Response r = given()
	        .contentType("application/json")
	        .body(user)
	        .when().put("/users").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

       JsonPath test = r.jsonPath();

       assertEquals("Testing", test.getString("userName"));
       assertEquals("Testing", test.getString("password"));
       System.out.println("Modify User");
       System.out.println("UserName: Testing == " + test.getString("userName"));
       System.out.println("Password: Testing == " + test.getString("password"));
	}
	
	@Test
    public void EbasicGetUserById() {
		Response r = given().pathParam("id", 29)
	    	.when().get("/users/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals("Testing", test.getString("userName"));
        assertEquals("Testing", test.getString("password"));
        System.out.println("Get User by Id");
        System.out.println("UserName: Testing == " + test.getString("userName"));
        System.out.println("Password: Testing == " + test.getString("password"));
    }

	@Test
    public void FbasicDeleteItem() {
		Response r = given().pathParam("id", 29)
	        .when().delete("/users/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();

        assertEquals("", r.asString());
        System.out.println("Delete User");
        System.out.println("Category == " + r.asString());
    }

}
