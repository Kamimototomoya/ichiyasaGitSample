package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.NewBody;
import model.NewHead;
import model.Plan;
import model.RegisterContractLogic;
import model.ShowPlanLogic;

@WebServlet("/RegisterContractServlet")
public class RegisterContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int FAILURE = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Logic実行
		ShowPlanLogic bo = new ShowPlanLogic();
		List<Plan> table = bo.execute();

		// 実行後処理
		request.setAttribute("table", table);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register_contract.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータ取得
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		String employeeId = employee.getEmployeeId();
		String contractDate = request.getParameter("contract_date");
		int customerId = Integer.parseInt(request.getParameter("customer_id"));
		String[] checkedPlans = request.getParameterValues("checkbox");

		// プランが1つ以上選択されているか確認
		String errorMsg = null;
//		for (String plan : checkedPlans) {
//			if (plan == null) {
		if(checkedPlans==null) {
				errorMsg = "プランが選択されていません";
//				break;
				// エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", errorMsg);
			}else {
//		}

		

		// Entity初期化
		NewHead head = new NewHead(contractDate, customerId, employeeId);
		List<NewBody> bodies = new ArrayList<>();

		for (String plan : checkedPlans) {
			int planId = Integer.parseInt(plan);
			bodies.add(new NewBody(planId));
		}

		// Logic実行
		RegisterContractLogic bo = new RegisterContractLogic();
		int contractId = bo.execute(head, bodies);

		// 実行後処理
		if (contractId == FAILURE) {
			request.setAttribute("errorMsg", "登録に失敗しました。");
		} else {
			request.setAttribute("resultMsg", "登録に成功しました。契約番号:" + contractId);
		}
		
			}//★

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register_contract_result.jsp");
		dispatcher.forward(request, response);

	}

}
