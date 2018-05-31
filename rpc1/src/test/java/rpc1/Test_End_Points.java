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
	public void EbasicAddMedia() {
		Map<String,String> media = new HashMap<>();
        media.put("id", "1");
        media.put("item_Id", "1");
        media.put("fileName", "Mushroom");
        media.put("filePath", "data:image/jpeg;base64,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");

        Response r = given().pathParam("itemId", 1)
	        .contentType("application/json")
	        .body(media)
	        .when().post("/items/{itemId}/media").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

       JsonPath test = r.jsonPath();

       assertEquals(1, test.getInt("imageID"));
       System.out.println("Create Media");
       System.out.println("Item ID: 1 == " + test.getInt("imageID"));
	}
	
	@Test
    public void FbasicGetItemById() {
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
    public void GbasicDeleteItem() {
		Response r = given().pathParam("id", 1)
	        .when().delete("/items/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();

        assertEquals("", r.asString());
        System.out.println("Delete Item");
        System.out.println("Item == " + r.asString());
    }
	
}
