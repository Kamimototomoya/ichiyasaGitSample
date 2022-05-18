package model;

import java.io.Serializable;

public class Plan implements Serializable{
	private int planId;
	private String name;
	private int price;

	public Plan(int getPlanId, String name, int price) {
		this.planId = getPlanId;
		this.name = name;
		this.price = price;
	}

	public int getPlanId() {
		return planId;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
