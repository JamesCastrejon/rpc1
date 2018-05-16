package rpc1;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class Test_Category_End_Points {

	@Test
	public void basicPingTest(){
		given().when().get("/categories")
			.then().statusCode(200);
	}
	
	@Test
    public void basicCreateCategory() {
        Map<String,String> category = new HashMap<>();
        category.put("id","1");
        category.put("name", "Test");

        Response r = given()
	        .contentType("application/json")
	        .body(category)
	        .when().post("/categories").then()
	        .statusCode(200)
	        .and()
	        .extract().response();
	
	        JsonPath test = r.jsonPath();
	
	        assertEquals("Test", test.getString("name"));
	        System.out.println("Create Category");
	        System.out.println("name: Test == " + test.getString("name"));
    }
	
	@Test
    public void basicGetCategories() {
        Response r = given().when().get("/categories")
            .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals(1, test.getInt("[0].id"));
        System.out.println("Get Category list");
        System.out.println("ID: 1 == " + test.getInt("[0].id"));
    }

	@Test
	public void basicModifyCategory() {
		Map<String,String> category = new HashMap<>();
        category.put("id","1");
        category.put("name", "Testing");

        Response r = given()
	        .contentType("application/json")
	        .body(category)
	        .when().put("/categories").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

       JsonPath test = r.jsonPath();

       assertEquals("Testing", test.getString("name"));
       System.out.println("Update Category");
       System.out.println("name: Testing == " + test.getString("name"));
	}
	
	@Test
    public void basicGetCategoryById() {
		Response r = given().pathParam("id", 1)
	    	.when().get("/categories/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals("Testing", test.getString("name"));
        System.out.println("Get Category by ID");
        System.out.println("name: Testing == " + test.getString("name"));
    }

	@Test
    public void basicDeleteCategory() {
		Response r = given().pathParam("id", 0)
	        .when().delete("/categories/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();

        assertEquals("", r.asString());
        System.out.println("Delete Category");
        System.out.println("Category == " + r.asString());
    }

}
