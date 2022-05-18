package model;

import java.util.List;

import dao.CustomerDAO;

public class ShowCustomerLogic {
	
	public List<Customer> execute(int customerId) {
		CustomerDAO dao = new CustomerDAO();
		List<Customer> customerList = dao.findByCustomer(customerId);
		return customerList;
	}
	
	public List<Customer> execute() {
		CustomerDAO dao = new CustomerDAO();
		List<Customer> customerList = dao.findByCustomer();
		return customerList;
	}

}
