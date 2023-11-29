package com.QA.Endpoints;

import com.QA.Factory.PlaywrightFactory;
import com.QA.PayLoads.Store;
import com.QA.routes.Routes;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class Store_Endpoints {

	static APIRequestContext context;

	public APIResponse create_Order(Store userpayload) {

		context= PlaywrightFactory.Init_Playwright();

		APIResponse response = context.post(Routes.Order_Post_Url ,
				RequestOptions.create()
				.setHeader("accept", "application/json")
				.setHeader("Content-Type", "application/json")
				.setData(userpayload)
				);
		return response;
	}

	public APIResponse Get_Order(int orderId) {

		context= PlaywrightFactory.Init_Playwright();

		APIResponse response = context.get(Routes.Order_Get_Url+orderId ,
				RequestOptions.create()
				.setHeader("accept", "application/json")
				);
		return response;
	}
	
	public APIResponse Delete_Order(int orderId) {

		context= PlaywrightFactory.Init_Playwright();

		APIResponse response = context.delete(Routes.Order_Delete_Url+orderId ,
				RequestOptions.create()
				.setHeader("accept", "application/json")
				);
		return response;
	}
}
