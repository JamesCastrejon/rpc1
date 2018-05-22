package rpc1;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_End_Points {

	@Test
	public void AbasicItemPingTest(){
		given().when().get("/items")
			.then().statusCode(200);
	}

	@Test
    public void BbasicCreateItem() {
        Map<String,String> item = new HashMap<>();
        item.put("id", "1");
        item.put("name", "Test");
        item.put("cost", "0.00");
        item.put("details", "Just a Test.");

        Response r = given()
	        .contentType("application/json")
	        .body(item)
	        .when().post("/items").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

        JsonPath test = r.jsonPath();

        assertEquals("Test", test.getString("name"));
        assertEquals("0.0", test.getString("cost"));
        assertEquals("Just a Test.", test.getString("details"));
        System.out.println("Create Item");
        System.out.println("name: Test == " + test.getString("name"));
        System.out.println("cost: 0.0 == " + test.getString("cost"));
        System.out.println("details: Just a Test. == " + test.getString("details"));
    }
	
	@Test
    public void CbasicGetItemsByName() {
        Response r = given().pathParam("name", "Test")
        	.when().get("/items/searchByName/{name}")
            .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals("Test", test.getString("name").replace("[", "").replace("]", ""));
        assertEquals("0.0", test.getString("cost").replace("[", "").replace("]", ""));
        assertEquals("Just a Test.", test.getString("details").replace("[", "").replace("]", ""));
        System.out.println("Get Item by ID");
        System.out.println("name: Test == " + test.getString("name").replace("[", "").replace("]", ""));
        System.out.println("cost: 0.0 == " + test.getString("cost").replace("[", "").replace("]", ""));
        System.out.println("details: Just a Test. == " + test.getString("details").replace("[", "").replace("]", ""));
    }
	
	@Test
    public void CbasicGetItems() {
        Response r = given().when().get("/items")
            .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals(1, test.getInt("[0].id"));
        System.out.println("Get Item list");
        System.out.println("ID: 1 == " + test.getInt("[0].id"));
    }
	
	@Test
	public void DbasicModifyItem() {
		Map<String,String> item = new HashMap<>();
        item.put("id", "1");
        item.put("name", "Testing");
        item.put("cost", "0.00");
        item.put("details", "Being Tested.");
        //item.put("category", "Health.");

        Response r = given()
	        .contentType("application/json")
	        .body(item)
	        .when().put("/items").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

       JsonPath test = r.jsonPath();

       assertEquals("Testing", test.getString("name"));
       assertEquals("0.0", test.getString("cost"));
       assertEquals("Being Tested.", test.getString("details"));
       System.out.println("Update Item");
       System.out.println("name: Testing == " + test.getString("name"));
       System.out.println("cost: 0.0 == " + test.getString("cost"));
       System.out.println("details: Being Tested. == " + test.getString("details"));
	}
	
	@Test
    public void EbasicGetItemById() {
		Response r = given().pathParam("id", 1)
	    	.when().get("/items/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals("Testing", test.getString("name"));
        assertEquals("0.0", test.getString("cost"));
        assertEquals("Being Tested.", test.getString("details"));
        System.out.println("Get Item by ID");
        System.out.println("name: Testing == " + test.getString("name"));
        System.out.println("cost: 0.0 == " + test.getString("cost"));
        System.out.println("details: Being Tested. == " + test.getString("details"));
    }

	@Test
    public void FbasicDeleteItem() {
		Response r = given().pathParam("id", 1)
	        .when().delete("/items/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();

        assertEquals("", r.asString());
        System.out.println("Delete Item");
        System.out.println("Category == " + r.asString());
    }
	
}
