package com.QA.Factory;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	public static  APIRequestContext Init_Playwright() {
		
		Playwright playwright= Playwright.create();
		
		APIRequest request= playwright.request();
		
		APIRequestContext context= request.newContext();
		
		return context;
	}
	
	public static Playwright init_Test() {
		return Playwright.create();
	}
}
