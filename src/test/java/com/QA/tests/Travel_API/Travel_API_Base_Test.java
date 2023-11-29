package com.QA.tests.Travel_API;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.QA.Endpoints.Travel_Endpoints;
import com.QA.Factory.PlaywrightFactory;
import com.QA.PayLoads.Travel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class Travel_API_Base_Test {

	public static Faker faker;
	public Travel travel;
	public static Travel_Endpoints endpoints;
	public static int id;
	public static ObjectMapper obj;
	public static Logger log;
	
	
	
    @BeforeTest
    public void set_Up() {
    	
    	faker=new Faker();
    	endpoints=new Travel_Endpoints();
    	obj=new ObjectMapper();
    	log=LogManager.getLogger(this.getClass());
    	
    	log.debug("Travel_Playload");
    	travel=new Travel();
    	travel.setTourist_name(faker.name().firstName());
    	travel.setTourist_email(faker.internet().safeEmailAddress());
    	travel.setTourist_location(faker.country().capital().toString());
    	
    	try {
			String JsonPayload= obj.writerWithDefaultPrettyPrinter().writeValueAsString(travel);
			System.out.println(JsonPayload);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	log.debug("Travel_Api_Test_Started...");
    }
	
    
    @AfterTest
    public void Tear_down() {
    	PlaywrightFactory.init_Test().close();
    	log.debug("Travel_Api_Test_Ended...");
    }
}
