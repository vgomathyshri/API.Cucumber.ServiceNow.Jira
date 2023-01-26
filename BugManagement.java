package jira.steps;

import java.io.File;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BugManagement {
	
	public static RequestSpecification inputRequest;
	public static Response response;
	public static String bugId;
	
	
	@Given("setup baseuri")
	public void enterUri() {
		RestAssured.baseURI="https://gomathyshriv19.atlassian.net/rest/api/2/issue/";
	}
	@And("validate cedentials")
	public void auth() {
		RestAssured.authentication= RestAssured.preemptive().basic("gomathyshri.v19@gmail.com", "wt2uY71omvjDhuufo4EBC5E9");
	}
	@When("create bug with file{string}")
	public void createBug(String fileName)
	{
	
		File inputfile = new File("./src/main/resources/"+fileName);
		inputRequest = RestAssured.given().contentType("application/json");
		response = inputRequest.contentType("application/json").when().body(inputfile).post();
		bugId = response.jsonPath().get("id");
		response.prettyPrint();
	}
	
	@When("get bug by bugId")
	public void getParticularBug() {
		
		 response = RestAssured.get("/"+bugId);
		 response.prettyPrint();
		 System.out.println("get request"+bugId);
	
	}

	@When("Update Bug with file{string}")
	public void updateBug(String updateFileName) {
		File updateFile=new File("./src/main/resources/"+updateFileName);
		System.out.println("BugId from update request" +bugId);
		inputRequest = RestAssured.given().contentType("application/json");
		response = inputRequest.when().body(updateFile).put("/"+bugId);
		response.prettyPrint();
		
	}
	
	@When("Delete a Bug")
	public void deleteBug() {
		response = inputRequest.delete("/"+bugId);
	}
	
	@Then("Status Code is {int}")
	public void validateStatusCode(int statusCode) {
		
		 response=response.then().assertThat().statusCode(statusCode).extract().response();
		
	}
	
	
	
}
