package com.mpokket.requests;

import org.testng.annotations.Test;

import com.mpokket.util.ResourcePath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateAndDeleteEmployeeTest extends BaseAPIClass {

	@Test
	public void createDeleteEmployeeTest() {

		CreateEmpPOJO createEmployeePOJO = new CreateEmpPOJO("suhas", "2525", "152");

		Response createEmployeeResponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.post(ResourcePath.CREATE_EMPLOYEE);
		createEmployeeResponse.then()
		.assertThat().statusCode(200)
		.assertThat().contentType(ContentType.JSON)
		.log().all();

		int empId = createEmployeeResponse.jsonPath().get("data.id");

		RestAssured.given()
		.pathParam("empid", empId)
		.get(ResourcePath.GET_SINGLE_EMPLOYEE)
		.then()
		.assertThat().statusCode(200)
		.assertThat().contentType(ContentType.JSON)
		.log().all();

		RestAssured.given()
		.pathParam("empid", empId)
		.delete(ResourcePath.DELETE_SINGLE_EMPLOYEE)
		.then()
		.assertThat().statusCode(204)
		.log().all();
	}
}
