package model;

import dao.BodyDAO;
import dao.HeadDAO;

public class DeleteContractLogic {
	public final int FAILURE = 0;

	public int execute(int contractId) {

		HeadDAO headDao = new HeadDAO();
		BodyDAO bodyDao = new BodyDAO();
		
		boolean b1=headDao.delete(contractId);
		boolean b2=bodyDao.delete(contractId);


		if (b1&&b2) {
			return contractId;
		}else {
			return FAILURE;
		}
		
	}

}
