package com.mpokket.util;

 
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
/**
 * 
 * @author SUHAS RAVINDRA
 *
 */

public class BaseAPIClass {

	@BeforeSuite
	public void requestSetUp() {
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			System.out.println(result.getMethod().getMethodName()+" request is passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			System.out.println(result.getMethod().getMethodName()+" request is failed");
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			System.out.println(result.getMethod().getMethodName()+" request is skipped");
		}
	}

	@AfterSuite
	public void requestTearDown() {
	}
}

