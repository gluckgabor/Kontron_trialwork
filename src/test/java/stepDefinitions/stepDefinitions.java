package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utilities.BaseClass;

//import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class stepDefinitions extends BaseClass {
	
	private static Logger logger = LogManager.getLogger(stepDefinitions.class);

	private int createdOwnerId;

	@Given("I add an Owner with following data and save it")
	public void i_add_an_owner_with_following_data(DataTable dataTable) {
		String baseUrl = "http://localhost:9966/petclinic/api/owners";

		String firstName = "";
		String lastName = "";
		String address= "";
		String city= "";
		String telephone= "";

		List<Map<String, String>> owners = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> owner : owners) {
			firstName = owner.get("firstName");
			lastName = owner.get("lastName");
			address = owner.get("address");
			city = owner.get("city");
			telephone = owner.get("telephone");

			String requestBody = String.format(
					"{\"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"city\": \"%s\", \"telephone\": \"%s\"}",
					firstName, lastName, address, city, telephone
			);

			Response response = given()
					.header("Content-Type", "application/json")
					.body(requestBody)
					.when()
					.post(baseUrl)
					.then()
					.statusCode(201)
					.extract()
					.response();

			String responseBody = response.getBody().asString();
			System.out.println(responseBody);

			createdOwnerId = response.jsonPath().getInt("id");
		}
	}

	@When("I refresh browser")
	public void i_refresh_browser () {
		// In an API context, refreshing the browser can be interpreted as making another GET request
		String baseUrl = "http://localhost:9966/petclinic/api/owners";
		given()
				.when()
				.get(baseUrl)
				.then()
				.statusCode(200);
	}

	@Then("I see owner is loaded back correctly")
	public void i_see_owner_is_loaded_back_correctly () {
		String baseUrl = "http://localhost:9966/petclinic/api/owners/" + createdOwnerId;

		given()
				.when()
				.get(baseUrl)
				.then()
				.statusCode(200)
				.body("firstName", equalTo("Gabor"))
				.body("lastName", equalTo("Gluck"))
				.body("address", equalTo("110 W. Liberty St."))
				.body("city", equalTo("Madison"))
				.body("telephone", equalTo("6085551023"));
	}

	// Step definitions for updating an owner
	@Given("I update an Owner to have following data and save it")
	public void i_update_an_owner_to_have_following_data(io.cucumber.datatable.DataTable dataTable) {
		String baseUrl = "http://localhost:9966/petclinic/api/owners/" + createdOwnerId;

		String firstName = "";
		String lastName = "";
		String address= "";
		String city= "";
		String telephone= "";

		List<Map<String, String>> owners = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> owner : owners) {
			firstName = owner.get("firstName");
			lastName = owner.get("lastName");
			address = owner.get("address");
			city = owner.get("city");
			telephone = owner.get("telephone");


			String requestBody = String.format(
				"{\"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"city\": \"%s\", \"telephone\": \"%s\"}",
				firstName, lastName, address, city, telephone
			);

		given()
				.header("Content-Type", "application/json")
				.body(requestBody)
				.when()
				.put(baseUrl)
				.then()
				.statusCode(200); // Typically, a 204 No Content response is returned for successful updates
		}
	}

	// Step definitions for deleting an owner
	@Given("I delete an Owner")
	public void i_delete_an_owner() {
		String baseUrl = "http://localhost:9966/petclinic/api/owners/" + createdOwnerId;

		given()
				.when()
				.delete(baseUrl)
				.then()
				.statusCode(204); // Typically, a 204 No Content response is returned for successful deletions
	}

	@Then("I see owner is correctly missing from returned results")
	public void i_see_owner_is_correctly_missing_from_returned_results() {
		String baseUrl = "http://localhost:9966/petclinic/api/owners/" + createdOwnerId;

		given()
				.when()
				.get(baseUrl)
				.then()
				.statusCode(404); // Expecting 404 Not Found since the owner has been deleted
	}


}

