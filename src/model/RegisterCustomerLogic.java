package model;

import dao.CustomerDAO;

public class RegisterCustomerLogic {
	public int execute(NewCustomer input) {
		CustomerDAO dao= new CustomerDAO();
		int customerId= dao.create(input);
		return customerId;
	}

}