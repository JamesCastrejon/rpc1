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
	// TODO: give findbyItemName test
	@Test
    public void BbasicCreateItem() {
        Map<String,String> item = new HashMap<>();
        item.put("id", "18");
        item.put("name", "Spice");
        item.put("cost", "10.00");
        item.put("details", "Increases attack power.");

        Response r = given()
	        .contentType("application/json")
	        .body(item)
	        .when().post("/items").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

        JsonPath test = r.jsonPath();

        assertEquals("Spice", test.getString("name"));
        assertEquals("10.0", test.getString("cost"));
        assertEquals("Increases attack power.", test.getString("details"));
        System.out.println("Create Item");
        System.out.println("name: Spice == " + test.getString("name"));
        System.out.println("cost: 10.0 == " + test.getString("cost"));
        System.out.println("details: Increases attack power. == " + test.getString("details"));
    }
	
	@Test
    public void CbasicGetItemsByName() {
        Response r = given().pathParam("name", "Mushroom")
        	.when().get("/items/searchByName/{name}")
            .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals("Mushroom", test.getString("name").replace("[", "").replace("]", ""));
        assertEquals("5.0", test.getString("cost").replace("[", "").replace("]", ""));
        assertEquals("Restores 15 HP.", test.getString("details").replace("[", "").replace("]", ""));
        System.out.println("Get Item by ID");
        System.out.println("name: Mushroom == " + test.getString("name").replace("[", "").replace("]", ""));
        System.out.println("cost: 5.0 == " + test.getString("cost").replace("[", "").replace("]", ""));
        System.out.println("details: Restores 15 HP. == " + test.getString("details").replace("[", "").replace("]", ""));
    }
	
	@Test
    public void CbasicGetItems() {
        Response r = given().when().get("/items")
            .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals(9, test.getInt("[0].id"));
        System.out.println("Get Item list");
        System.out.println("ID: 9 == " + test.getInt("[0].id"));
    }
	
	@Test
	public void DbasicModifyItem() {
		Map<String,String> item = new HashMap<>();
        item.put("id", "10");
        item.put("name", "1-UP Mushroom");
        item.put("cost", "10.00");
        item.put("details", "Restores 0 HP to 15 HP.");
        //item.put("category", "Health.");

        Response r = given()
	        .contentType("application/json")
	        .body(item)
	        .when().put("/items").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

       JsonPath test = r.jsonPath();

       assertEquals("1-UP Mushroom", test.getString("name"));
       assertEquals("10.0", test.getString("cost"));
       assertEquals("Restores 0 HP to 15 HP.", test.getString("details"));
       System.out.println("Update Item");
       System.out.println("name: 1-UP Mushroom == " + test.getString("name"));
       System.out.println("cost: 10.0 == " + test.getString("cost"));
       System.out.println("details: Restores 0 HP to 15 HP. == " + test.getString("details"));
	}
	
	@Test
    public void EbasicGetItemById() {
		Response r = given().pathParam("id", 9)
	    	.when().get("/items/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals("Mushroom", test.getString("name"));
        assertEquals("5.0", test.getString("cost"));
        assertEquals("Restores 15 HP.", test.getString("details"));
        System.out.println("Get Item by ID");
        System.out.println("name: Mushroom == " + test.getString("name"));
        System.out.println("cost: 5.0 == " + test.getString("cost"));
        System.out.println("details: Restores 15 HP. == " + test.getString("details"));
    }

	@Test
    public void FbasicDeleteItem() {
		Response r = given().pathParam("id", 14)
	        .when().delete("/items/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();

        assertEquals("", r.asString());
        System.out.println("Delete Item");
        System.out.println("Category == " + r.asString());
    }
	
}
