package com.QA.tests.Store_API;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.microsoft.playwright.APIResponse;

public class Store_API_Test extends Store_API_Base_Test{


	@Test(priority = 1)
	public void Create_Order_Test() {

		log.info("Create Order Test Started..");
		
		APIResponse response= endpoints.create_Order(this.store);
		System.out.println(response.status()+" "+response.statusText());

		// Assertion on response status and Text
		Assert.assertTrue(response.status()==200);
		log.info("Create Order Test Response to be 200..");
		Assert.assertTrue(response.statusText().equals("OK"));

		try {

			JsonNode data= obj.readTree(response.body());
			id= data.get("id").asInt();
			System.out.println("Data ID : "+id);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test(priority = 2)
	public void Get_Order_Test() {
		
		log.info("Get Order Test Started..");

		APIResponse response= endpoints.Get_Order(id);

		System.out.println(response.status()+" "+response.statusText());
		
		Assert.assertTrue(response.statusText().equals("OK"));
		
		log.info("Get Order Test Response to be 200..");
		Assert.assertTrue(response.status()==200);

		try {

			JsonNode data= obj.readTree(response.body());
			System.out.println(data.toPrettyString());

			// Assertions on Response Body

			Assert.assertTrue(data.get("id")
					.asInt()==this.store.getId());
			log.info("Get Order Test ID Assertion passed..");
			
			Assert.assertTrue(data.get("petId").
					asInt()==this.store.getPetId());
			log.info("Get Order Test PetID Assertion passed..");
			
			Assert.assertTrue(data.get("quantity").
					asInt()==this.store.getQuantity());
			log.info("Get Order Test quantity Assertion passed..");
			
			Assert.assertTrue(data.get("shipDate").
					asText().equals(this.store.getShipDate()));
			log.info("Get Order Test shipDate Assertion passed..");
			
			Assert.assertTrue(data.get("status").
					asText().equals(this.store.getStatus()));
			log.info("Get Order Test status Assertion passed..");
			
			Assert.assertTrue(data.get("complete").
					asBoolean()==true);
			log.info("Get Order Test complete Assertion passed..");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void Delete_Order_Test() {
		
		log.info("Delete Order Test Started..");

		APIResponse response= endpoints.Delete_Order(id);

		System.out.println(response.status() +" "+response.statusText());

		// Expecting response status to be 200
		log.info("Get Order Test ID response to be 200..");
		Assert.assertTrue(response.status()==200);

		// Expecting response status text to be OK
		Assert.assertTrue(response.statusText().equals("OK"));

		try {
			
			JsonNode data= obj.readTree(response.body());
			System.out.println(data.toPrettyString());
			
			// Assertion on id Data To be Deleted...
			Assert.assertTrue(
			           data.get("message").asInt()==this.store.getId()
					);
			  log.info("Delete Order Test message Assertion passed..");
			  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
