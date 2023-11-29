package com.QA.tests.Travel_API;


import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.HttpHeader;


public class TravelTest extends Travel_API_Base_Test{
	
	
	@Test(priority = 1)
	public void Create_User_Test() {
		
		log.info("Travel API Create User Test Started...");
		APIResponse response= endpoints.create_User(this.travel);

		if(response.status()==201) {
			Assert.assertTrue(true);
			log.info("Travel API Create User Test Response code to be 200...");
		} else {
			Assert.assertTrue(false);
		}

		Assert.assertTrue(response.statusText().equals("Created"));

		JSONObject json=new JSONObject(response.text());
		id= json.getInt("id");
		System.out.println(id);

		//applying Assertion for response body
		Assert.assertTrue(json.getString("tourist_name").equals(this.travel.getTourist_name()));
		log.info("Travel API Create User Test Tourist name Assertion passed...");
		
		Assert.assertTrue(json.getString("tourist_email").equals(this.travel.getTourist_email()));
		log.info("Travel API Create User Test Tourist email Assertion passed...");
		
		Assert.assertTrue(json.getString("tourist_location").equals(this.travel.getTourist_location()));
		log.info("Travel API Create User Test Tourist location Assertion passed...");

	}

	@Test(priority = 2)
	public void get_User_Test() {
		
		log.info("Travel API Get User Test Started...");
		
		APIResponse response= endpoints.get_User(id);
		System.out.println(response.status());
		
		Assert.assertTrue(response.status()==200);
		log.info("Travel API Get User Test Response status to be 200...");

		try {
			String jsonData= obj.writerWithDefaultPrettyPrinter()
					.writeValueAsString(obj.readTree(response.body()));
			System.out.println(jsonData);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("Travel API Get User Response Header Validation...");

		List<HttpHeader> headerList= response.headersArray();

		for(HttpHeader header :headerList) {
			System.out.println(header.name+" : "+header.value);
		}

		Assert.assertTrue(headerList.get(2).name.contains("Content-Type"));
		log.info("Travel API Get User Content type Assertion passed...");
		
		Assert.assertTrue(headerList.get(2).value.contains("application/json; charset=utf-8"));

		// Assertions on Response body

		JSONObject json=new JSONObject(response.text());
		//applying Assertion for response body
		Assert.assertTrue(json.getString("tourist_name").equals(this.travel.getTourist_name()));
		log.info("Travel API Get User Tourist_name Assertion passed...");
		
		Assert.assertTrue(json.getString("tourist_email").equals(this.travel.getTourist_email()));
		log.info("Travel API Get User Tourist_email Assertion passed...");
		
		Assert.assertTrue(json.getString("tourist_location").equals(this.travel.getTourist_location()));
		log.info("Travel API Get User Tourist_location Assertion passed...");
		
	}
	
	
}
