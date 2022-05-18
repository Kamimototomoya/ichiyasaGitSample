package model;

import java.io.Serializable;

public class Detail implements Serializable{
	private int contractId;
	private String contractDate;
	private int customerId;
	private String customerName;
	private int planId;
	private String planName;
	private int price;

	public Detail(int contractId, String contractDate, int customerId, String customerName, int planId, String planName,
			int price) {
		this.contractId = contractId;
		this.contractDate = contractDate;
		this.customerId = customerId;
		this.customerName = customerName;
		this.planId = planId;
		this.planName = planName;
		this.price = price;
	}

	public int getContractId() {
		return contractId;
	}

	public String getContractDate() {
		return contractDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public int getPlanId() {
		return planId;
	}

	public String getPlanName() {
		return planName;
	}

	public int getPrice() {
		return price;
	}

}
