package model;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private String employeeId;
	private String name;
	
	public Employee(String employeeId, String name) {
		this.employeeId = employeeId;
		this.name = name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}
}
