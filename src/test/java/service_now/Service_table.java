package service_now;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import servicenow_payload.Service_Payload;

public class Service_table {
	
	@Test
	public void addPlace() throws IOException {
		
		
		RestAssured.baseURI= "https://dev276566.service-now.com";
		
		RequestSpecification reqw = new RequestSpecBuilder().setBaseUri("https://dev276566.service-now.com")
				.addHeader("Accept","application/json").addHeader("Content-Type","application/json").build();
		
		String response = given().log().all().auth().basic("admin","Lo!xIMC^z76p")
				.header("Accept","application/json").header("Content-Type","application/json")
		.body(Service_Payload.post_payload()).when().post("api/now/table/incident").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String sysid = js.getString("result.sys_id");
		
		
//getMethod
		
		given().log().all().auth().basic("admin","Lo!xIMC^z76p")
				.header("Accept","application/json").header("Content-Type","application/json")
			.when().get("api/now/table/incident/"+sysid+"").then()
		.log().all().assertThat().statusCode(200);
		
//Delete Method
		
		
		  given().log().all().auth().basic("admin","Lo!xIMC^z76p")
		  .header("Accept","application/json").header("Content-Type",
		 "application/json") .when().delete("api/now/table/incident/"+sysid+"")
		  .then().log().all().assertThat().statusCode(204);
		 


	}}