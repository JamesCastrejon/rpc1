package rpc1;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class Test_End_Points {

	@Test
	public void basicPingTest(){
		given().when().get("/items")
			.then().statusCode(200);
	}

}
