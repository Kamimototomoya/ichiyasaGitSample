package model;

import java.util.List;

import dao.BodyDAO;
import dao.HeadDAO;

public class RegisterContractLogic {
	public final int FAILURE = 0;

	public int execute(NewHead head, List<NewBody> bodies) {

		HeadDAO headDao = new HeadDAO();
		int contractId = headDao.create(head);

		if (contractId != FAILURE) {
			for (int i = 0; i < bodies.size(); i++) {
				BodyDAO bodyDao = new BodyDAO();
				boolean done=bodyDao.create(contractId, bodies.get(i));
				if(!done) {
					return FAILURE;
				}
			}
		}
		return contractId;
	}

}
