package com.QA.Endpoints;

import com.QA.Factory.PlaywrightFactory;
import com.QA.PayLoads.User;
import com.QA.routes.Routes;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class UserEndpoints {

	static APIRequestContext context;

	public APIResponse Create_User(User userPayload) {

		context=PlaywrightFactory.Init_Playwright();

		APIResponse response= context.post(Routes.User_Post_Url ,
				RequestOptions.create()
				.setHeader("accept", "application/json")
				.setHeader("Content-Type", "application/json")
				.setData(userPayload)
				);
		return response;
	}

	public APIResponse Get_User(String userName) {

		context=PlaywrightFactory.Init_Playwright();

		APIResponse response= context.get(Routes.User_Get_Url+userName,
				RequestOptions.create()
				.setHeader("accept", "application/json")
				);
		return response;
	}
	
	public APIResponse Update_User(String userName,User userPayload) {

		context=PlaywrightFactory.Init_Playwright();

		APIResponse response= context.put(Routes.User_Put_Url+userName,
				RequestOptions.create()
				.setHeader("accept", "application/json")
				.setHeader("Content-Type", "application/json")
				.setData(userPayload)
				);
		return response;
	}
	
	public APIResponse Delete_User(String userName) {

		context=PlaywrightFactory.Init_Playwright();

		APIResponse response= context.delete(Routes.User_Delete_Url+userName,
				RequestOptions.create()
				.setHeader("accept", "application/json")
				);
		return response;
	}
}
