package com.APIAutomation.pojoLibrary;

public class PoJo_CreateEmployee {

	private String name;
	private String salary;
	private String age;



	/*	public PoJo_CreateEmployee(String name, int salary, int age) {
		this.name = name;
		this.salary = salary;
		this.age = age;
	}*/
	/*
	 * Below code and this class purpose is to serialize the object these serialized objects are 
	 * passed to test method as a payload . To serialize we used setter and getter methods as below
	 */
	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
