package com.QA.tests.User_API;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.QA.Endpoints.UserEndpoints;
import com.QA.Factory.PlaywrightFactory;
import com.QA.PayLoads.User;
import com.QA.tests.User_API.User_API_Base_Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.HttpHeader;

public class UserTest extends User_API_Base_Test{
	
	
	
	@Test(priority = 1)
	public void create_User_Test() {
		
		log.info("User API create User Test Started...");
		
		APIResponse response= endpoints.Create_User(this.user);
		System.out.println(response.status()+" : "+response.statusText());
		
		log.info("User API create User Test Response Code to be 200...");
		Assert.assertEquals(response.status(), 200,"Check For 200 status code");
		Assert.assertTrue(response.statusText().equals("OK"));
		
		try {
			String JsonResponsebody=obj.writerWithDefaultPrettyPrinter()
			             .writeValueAsString(obj.readTree(response.text()));
			System.out.println(JsonResponsebody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void get_User_Test() {
		
		log.info("User API Get User Test Started...");
		APIResponse response= endpoints.Get_User(this.user.getUsername());
		System.out.println(response.status()+" : "+response.statusText());
		
		log.info("User API Get User Test Response to be 200...");
		Assert.assertTrue(response.status()==200);
		
		Assert.assertTrue(response.statusText().equals("OK"));
		// Validating Response Body of Get User
		
		try {
			String GetResponseBody= obj.writerWithDefaultPrettyPrinter()
			                   .writeValueAsString(
			                		   obj.readTree(response.text())
			                		   );
			System.out.println(GetResponseBody);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//Assertion on Response_Body 
		JSONObject object=new JSONObject(response.text());
		
		Assert.assertTrue(object.getInt("id")==this.user.getId());
		log.info("User API Get User Test id Assertion passed...");
		
		Assert.assertTrue(object.getString("username").equals(this.user.getUsername()));
		log.info("User API Get User Test username Assertion passed...");
		
		Assert.assertTrue(object.getString("firstName").equals(this.user.getFirstName()));
		log.info("User API Get User Test firstname Assertion passed...");
		
		Assert.assertTrue(object.getString("lastName").equals(this.user.getLastName()));
		log.info("User API Get User Test lastname Assertion passed...");
		
		Assert.assertTrue(object.getString("email").equals(this.user.getEmail()));
		log.info("User API Get User Test email Assertion passed...");
		
		Assert.assertTrue(object.getString("password").equals(this.user.getPassword()));
		log.info("User API Get User Test password Assertion passed...");
		
		Assert.assertTrue(object.getString("phone").equals(this.user.getPhone()));
		log.info("User API Get User Test phone Assertion passed...");
		
		// Validating response Headers
		
		log.info("User API Get User Test Headers validation...");
		
		List<HttpHeader> headerList= response.headersArray();
		
		for(HttpHeader header :headerList) {
			System.out.println(header.name+" : "+header.value);
		}
		
		if(headerList.get(1).name.equals("Content-Type")==true) {
			headerList.get(1).value.equals("application/json");
			log.info("User API Get User Test Headers Assertion passed...");
		} else {
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 3)
	public void Update_User_Test() {
		
		log.info("User API Update User Test Started...");
		
		APIResponse response= endpoints.Update_User(user.getUsername(), this.user);
		System.out.println(response.status()+" : "+response.statusText());
		
		Assert.assertTrue(response.status()==200);
		
		try {
			String GetResponseBody= obj.writerWithDefaultPrettyPrinter()
			                   .writeValueAsString(
			                		   obj.readTree(response.text())
			                		   );
			System.out.println(GetResponseBody);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test(priority = 4)
	public void Delete_User_Test() {
		
		log.info("User API Delete User Test Started...");
		APIResponse response= endpoints.Delete_User(user.getUsername());
		System.out.println(response.status()+" : "+response.statusText());
		
		log.info("User API Delete User Test Response code to be 200...");
		Assert.assertTrue(response.status()==200);
		log.info("User API Delete User Test Endend...");
	}
	
	
}
