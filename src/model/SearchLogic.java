package model;

import java.util.List;

import dao.HeadDAO;

public class SearchLogic {
	public List<Result> execute(Condition input) {
		HeadDAO dao= new HeadDAO();
		List<Result> resultTable = dao.findByHead(input);
		return resultTable;
	}

}
