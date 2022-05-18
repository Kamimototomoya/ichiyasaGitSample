package model;

import java.util.List;

import dao.PlanDAO;

public class ShowPlanLogic {
	public List<Plan> execute() {
		PlanDAO dao = new PlanDAO();
		List<Plan> planList = dao.findByPlan();
		return planList;
	}

}
