package model;

import dao.EmployeeDAO;

public class RegisterEmployeeLogic {
	
	public boolean execute(NewEmployee input) {
		
		EmployeeDAO dao= new EmployeeDAO();
		boolean done = dao.create(input);
		return done;
	}

}
