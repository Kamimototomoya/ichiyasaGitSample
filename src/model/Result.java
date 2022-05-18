package model;

import java.io.Serializable;

public class Result implements Serializable{
	private int contractId;
	private String contractDate;
	private String customerName;
	private int total;
	
	public Result(int contractId, String contractDate, String customerName, int total) {
		this.contractId = contractId;
		this.contractDate = contractDate;
		this.customerName = customerName;
		this.total = total;
	}

	public int getContractId() {
		return contractId;
	}

	public String getContractDate() {
		return contractDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getTotal() {
		return total;
	}
}
