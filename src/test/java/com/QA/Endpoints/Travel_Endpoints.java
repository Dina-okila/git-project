package com.QA.Endpoints;

import com.QA.Factory.PlaywrightFactory;
import com.QA.PayLoads.Travel;
import com.QA.routes.Routes;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class Travel_Endpoints {

	static APIRequestContext context;
	
	public APIResponse create_User(Travel userplayload) {
		
		context= PlaywrightFactory.Init_Playwright();
		
		APIResponse response= context.post(Routes.post_Url,
				RequestOptions.create()
				.setHeader("content-type", "application/json")
				.setData(userplayload)
				);
		return response;
	}
	
	public APIResponse get_User(int userId) {
		
		APIResponse response= context.get(Routes.get_Url+userId ,
				RequestOptions.create()
				.setHeader("content-type", "application/json")
				);
		return response;
	}
	
	
}
