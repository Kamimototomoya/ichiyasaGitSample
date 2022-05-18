package model;

import java.util.List;

import dao.BodyDAO;

public class ShowDetailLogic {
	
	public List<Detail> execute(int contractId) {
		BodyDAO dao = new BodyDAO();
		List<Detail> detailList= dao.findByBody(contractId);
		return detailList;
	}

}
