package model;

public class NewCustomer {
	private String name;
	private String registrationDate;
	private int age;
	private String address;
	private String tel;
	
	public NewCustomer(String name, String registrationDate, int age, String address, String tel) {
		this.name = name;
		this.registrationDate = registrationDate;
		this.age = age;
		this.address = address;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}
	public String getRegistrationDate() {
		return registrationDate;
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
