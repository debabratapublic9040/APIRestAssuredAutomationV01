package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Created for perform Create, Read, update and Delete request in the user API

public class UsersEndPoints {
	
	public static Response createUser(User payload)
	{
		Response response=given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
						
						.when()
							.post(Routes.post_url);
							return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response=given()
							.pathParam("username", userName)
						
						.when()
							.get(Routes.get_url);
							return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response response=given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
							.pathParam("username", userName)
		
						.when()
							.put(Routes.update_url);
							return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
							.pathParam("username", userName)
							
						.when()
							.delete(Routes.delete_url);
							return response;
	}

}
