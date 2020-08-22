package com.APIAutomation.APIBusinessScenarios;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.APIAutomation.UtilityLibrary.Utils;
import com.APIAutomation.pojoLibrary.PoJo_CreateEmployee;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import junit.framework.Assert;

public class BusinessScenarios{
	/*
	 * Binding the data required for encapsulating this class , as the input data and output data
	 * revolving around these below mentioned data
     */
	public String empId ;
	public String empName ;
	public String empSalary ;
	public String empAge ;
	public String Response ;
	
	/*
	 * Below code is to hold the reusable resource variable used for all the @Test annoted methods
     */
	public static void intializeApiService() throws Exception {
		String baseUri=Utils.getGlobalValue("baseURI");
		String basePath=Utils.getGlobalValue("basePath");
		RestAssured.baseURI = baseUri;
		RestAssured.basePath = basePath;
	}

	/*
	 * Below code is to Create an employee in the database using the API services
     */
	@Test
	public void CreateEmployee() throws Exception{
		
		/*
		 * Below code is to serialize the data using POJO class
	     */
		PoJo_CreateEmployee CreateEmpData = new PoJo_CreateEmployee();
		CreateEmpData.setName("Shreedhar");
		CreateEmpData.setSalary("50000");
		CreateEmpData.setAge("35");

		intializeApiService();
		String Response = RestAssured.given()
					     .body(CreateEmpData)
					     .header("Content-Type","application/json")
					     .header("Accept","application/json")

					     .when()
					     .post(Utils.getGlobalValue("endPoint_create")) 

					     .then()
					     .extract().asString();

		
		empId = Utils.getJsonPath(Response,"id");
		empName=Utils.getJsonPath(Response,"name");
		empSalary = Utils.getJsonPath(Response,"salary");;
		empAge = Utils.getJsonPath(Response,"age");;

		Assert.assertEquals(CreateEmpData.getName(), empName);
		Assert.assertEquals(CreateEmpData.getSalary(), empSalary);
		Assert.assertEquals(CreateEmpData.getAge(), empAge);

		}
	
	/*
	 * Below code is to Read an employee data created in previous test using the API services
     */
	@Test
	public void getEmployeeDetails() throws Exception {
		
		intializeApiService();
		String Response = RestAssured.given()
						  .header("Accept","application/json")
						  .when()
						  .get(Utils.getGlobalValue("endPoint_getEmpdetails/"+empId))
						  .then()
						  .extract().asString();
		
		Assert.assertEquals(empName, Utils.getJsonPath(Response,"employee_name"));
		Assert.assertEquals(empSalary, Utils.getJsonPath(Response,"employee_salary"));
		Assert.assertEquals(empAge,Utils.getJsonPath(Response,"employee_age"));
		Assert.assertEquals(empId, Utils.getJsonPath(Response,"id"));
		
	}
	
	/*
	 * Below code is to Update an employee data created in previous test using the API services
     */
	@Test
	public void updateEmployeeDetails() throws Exception {
		
		/*
		 * Below code is to serialize the data using POJO class
	     */
		PoJo_CreateEmployee UpdateEmpData = new PoJo_CreateEmployee();
		UpdateEmpData.setName("Athani");
		UpdateEmpData.setAge("30");
		
		intializeApiService();
		String Response = RestAssured.given()
						  .body(UpdateEmpData)
						  .header("Content-Type","application/json")
						  .header("Accept","application/json")
						  .when()
						  .put(Utils.getGlobalValue("endPoint_update/"+empId))
						  .then()
						  .extract().asString();

		Assert.assertEquals(empName, Utils.getJsonPath(Response,"name"));
		Assert.assertEquals(empAge, Utils.getJsonPath(Response,"name"));
		Assert.assertEquals(null, Utils.getJsonPath(Response,"name"));
		
	}
}
