package model;

public class Condition {
	
	private String start;
	private String end;
	private String customerName;
	private String planName;
	private String employeeId;
	
	public Condition(String start, String end, String customerName, String planName, String employeeId) {
		this.start = start;
		this.end = end;
		this.customerName = customerName;
		this.planName = planName;
		this.employeeId=employeeId;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getPlanName() {
		return planName;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

}
