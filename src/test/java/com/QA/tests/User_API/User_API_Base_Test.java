package com.QA.tests.User_API;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.QA.Endpoints.UserEndpoints;
import com.QA.Factory.PlaywrightFactory;
import com.QA.PayLoads.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class User_API_Base_Test {

	protected static UserEndpoints endpoints;
	protected User user;
	protected static Faker faker;
	protected static ObjectMapper obj;
	public static Logger log;

	@BeforeClass
	public void set_Up() {
		
		endpoints=new UserEndpoints();
		faker=new Faker();
		obj=new ObjectMapper();
		
		user=new User();
		user.setId(faker.idNumber().hashCode());
		user.setUsername(faker.name().username());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password());
		user.setPhone(faker.phoneNumber().cellPhone());
		
		System.out.println("------User_PayLoad_Sending-------");
		
		try {
			String payload= obj.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			System.out.println(payload);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		log=LogManager.getLogger(this.getClass());
		log.debug("User API test Started...");
	}
	
	@AfterClass
	public void tear_Down() {
		PlaywrightFactory.init_Test().close();
		log.debug("User API test Completed...");
	}
}
