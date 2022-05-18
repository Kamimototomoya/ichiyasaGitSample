package model;

import java.io.Serializable;

public class Customer implements Serializable{
	private int customerId;
	private String name;
	private String registarationDate;
	private int age;
	private String address;
	private String tel;
	
	public Customer(int customerId, String name, String registarationDate, int age, String address, String tel) {
		this.customerId = customerId;
		this.name = name;
		this.registarationDate = registarationDate;
		this.age = age;
		this.address = address;
		this.tel = tel;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public String getName() {
		return name;
	}
	public String getRegistarationDate() {
		return registarationDate;
	}
	public int getAge() {
		return age;
	}
	public String getAddress() {
		return address;
	}
	public String getTel() {
		return tel;
	}

}
