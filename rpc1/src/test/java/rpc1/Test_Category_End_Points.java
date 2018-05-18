package rpc1;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_Category_End_Points {

	@Test
	public void AbasicPingTest(){
		given().when().get("/categories")
			.then().statusCode(200);
	}
	
	@Test
    public void BbasicCreateCategory() {
        Map<String,String> category = new HashMap<>();
        category.put("category_id","3");
        category.put("name", "test");

        Response r = given()
	        .contentType("application/json")
	        .body(category)
	        .when().post("/categories").then()
	        .statusCode(200)
	        .and()
	        .extract().response();
	
	        JsonPath test = r.jsonPath();
	
	        assertEquals("test", test.getString("name"));
	        System.out.println("Create Category");
	        System.out.println("name: Test == " + test.getString("name"));
    }
	@Test
    public void BbasicAddItemToCategory() {
        Map<String,String> item = new HashMap<>();
        item.put("id", "18");
        item.put("name", "Test_Item");
        item.put("cost", "10.00");
        item.put("details", "Increases attack power.");
        //item.put("category", "24");

        Response r = given().pathParam("categoryId", 24)
	        .contentType("application/json")
	        .body(item)
	        .when().post("/categories/{categoryId}/items").then()
	        .statusCode(200)
	        .and()
	        .extract().response();
	
        JsonPath test = r.jsonPath();
	
        assertEquals(24, test.getInt("category"));
        System.out.println("Create Category");
        System.out.println("name: category == " + test.getInt("category"));
    }
	
	@Test
    public void CbasicGetCategories() {
        Response r = given().when().get("/categories")
            .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals(1, test.getInt("[0].category_id"));
        System.out.println("Get Category list");
        System.out.println("ID: 1 == " + test.getInt("[0].category_id"));
    }

	@Test
	public void DbasicModifyCategory() {
		Map<String,String> category = new HashMap<>();
        category.put("category_id","2");
        category.put("id","26");
        category.put("name", "Herb");

        Response r = given()
	        .contentType("application/json")
	        .body(category)
	        .when().put("/categories").then()
	        .statusCode(200)
	        .and()
	        .extract().response();

       JsonPath test = r.jsonPath();

       assertEquals("Herb", test.getString("name"));
       System.out.println("Update Category");
       System.out.println("name: Herb == " + test.getString("name"));
	}
	
	@Test
    public void EbasicGetCategoryById() {
		Response r = given().pathParam("id", 24)
	    	.when().get("/categories/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();
		
        JsonPath test = r.jsonPath();

        assertEquals("Restoration", test.getString("name"));
        System.out.println("Get Category by ID");
        System.out.println("name: Restoration == " + test.getString("name"));
    }

	@Test
    public void FbasicDeleteCategory() {
		Response r = given().pathParam("id", 28)
	        .when().delete("/categories/{id}")
	        .then().statusCode(200)
            .and()
            .extract().response();

        assertEquals("", r.asString());
        System.out.println("Delete Category");
        System.out.println("Category == " + r.asString());
    }

}
