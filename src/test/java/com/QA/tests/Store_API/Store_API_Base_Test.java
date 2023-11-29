package com.QA.tests.Store_API;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.QA.Endpoints.Store_Endpoints;
import com.QA.Factory.PlaywrightFactory;
import com.QA.PayLoads.Store;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class Store_API_Base_Test {

	public static Faker faker;
	public static Store_Endpoints endpoints;
	public Store store;
	public static ObjectMapper obj;
	public static int id;
	public static Logger log;

	
	@BeforeTest
	public void set_Up() {

		faker=new Faker();
		endpoints=new Store_Endpoints();
		obj=new ObjectMapper();

		store =new Store();
		store.setId(faker.number().numberBetween(1, 10));
		store.setPetId(faker.number().numberBetween(1, 10));
		store.setQuantity(faker.number().numberBetween(1, 10));
		store.setShipDate("2023-11-28T08:49:56.563+0000");
		store.setStatus("placed");
		store.setComplete(true);

		System.out.println("****Store PayLoad******");
		try {

			String jsonData= obj.writerWithDefaultPrettyPrinter().writeValueAsString(store);
			System.out.println(jsonData);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		log=LogManager.getLogger(this.getClass());
		log.debug("Store API Test Started...");
	}
	
	@AfterTest
	public void tear_Down() {
		PlaywrightFactory.init_Test().close();
		log.debug("Store API Test Closed...");
	}
}
