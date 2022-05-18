package model;

public class Login {
	
	private String employeeId;
	private String pass;
	
	public Login(String employeeId, String pass) {
		this.employeeId = employeeId;
		this.pass = pass;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getPass() {
		return pass;
	}

}
