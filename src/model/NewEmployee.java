package model;

public class NewEmployee {
	
	private String employeeId;
	private String name;
	private String pass;
	
	public NewEmployee(String employeeId, String name, String pass) {
		this.employeeId = employeeId;
		this.name = name;
		this.pass = pass;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}
}
