package model;

import dao.EmployeeDAO;

public class LoginLogic {
	
	public Employee execute(Login input) {
		
		EmployeeDAO dao= new EmployeeDAO();
		Employee employee = dao.findByEmployee(input); //DAOメソッド呼び出し
		return employee; //該当ユーザーがあればtrue,なければfalseを返す
	}

}
