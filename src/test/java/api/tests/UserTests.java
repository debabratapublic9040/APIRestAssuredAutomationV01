package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UsersEndPoints;
import api.endpoints.UsersEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastNmae(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void test_postUser()
	{
		logger.info("***************Create User***************");
		Response response=UsersEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************User Created***************");
		
	}
	
	@Test(priority=2)
	public void test_getUserByUserName()
	{
		logger.info("***************Display User Data***************");
		Response response=UsersEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************User Data Dispalyed***************");
		
	}
	
	@Test(priority=3)
	public void test_updateUserByName()
	{
		logger.info("***************Generate User new modifiey Data***************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastNmae(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		logger.info("***************Modify the User Data***************");
		Response response=UsersEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************Modified User Data***************");
		
		//Checking the data post update
		logger.info("***************Modify the User Data***************");
		Response responseAfterUpdate=UsersEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		logger.info("***************User Data Dispalyed Post Modification***************");
		
		
	}
	
	@Test(priority=4)
	public void test_deleteUserByUserName()
	{
		logger.info("***************Delete Existing User Data***************");
		Response response=UsersEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************Deleted User Data***************");
		
	}

}
