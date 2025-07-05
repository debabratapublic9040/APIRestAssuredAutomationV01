package api.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UsersEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID,String userName,String fName,String lName,String emailAddress,String pwd,String ph)
	{
		User userPayload=new User();
		
		
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastNmae(lName);
		userPayload.setEmail(emailAddress);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UsersEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUser(String userName)
	{
		
		Response response=UsersEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}

}
