package model;

public class NewHead {
	
	private String date;
	private int customerId;
	private String employeeId;
	private int contractId;
	
	public NewHead(String date, int customerId, String employeeId) {
		this.date = date;
		this.customerId = customerId;
		this.employeeId = employeeId;
	}

	public String getDate() {
		return date;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	
	public int getContractId() {
		return contractId;
	}
	
}
