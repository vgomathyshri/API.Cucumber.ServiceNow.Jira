package servicenow.steps;

import java.io.File;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServiceNowChangeRequest {
	
	public static RequestSpecification inputRequest;
	public static Response response;
	public static String sysId;
	
	@Given("setup baseuri")
	public void enterUri() {
		RestAssured.baseURI="https://dev142770.service-now.com/api/now/table/change_request";
	}
	@And("validate cedentials")
	public void auth() {
		RestAssured.authentication=RestAssured.basic("admin", "Cz*tn1U0vS!V");
	}
	
	@When("create change request with file{string}")
	public void postWithFile(String fileName) {
		
		File inputFile=new File("./src/main/resources/"+fileName);
		inputRequest = RestAssured.given().contentType("application/json");
		response = inputRequest.when().body(inputFile).post();
		response.prettyPrint();
	}
	@Then("Status Code is {int}")
	public void validateStatusCode(int statusCode) {
		
		 response=response.then().assertThat().statusCode(statusCode).extract().response();
		
	}

}
