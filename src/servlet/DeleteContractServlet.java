package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeleteContractLogic;

@WebServlet("/DeleteContractServlet")
public class DeleteContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int FAILURE = 0;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
////		// Logic実行
////		ShowPlanLogic bo = new ShowPlanLogic();
////		List<Plan> table = bo.execute();
////
////		// 実行後処理
////		request.setAttribute("table", table);
////
////		// フォワード
////		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register_contract.jsp");
////		dispatcher.forward(request, response);
//
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータ取得
//		HttpSession session = request.getSession();
//		Employee employee = (Employee) session.getAttribute("employee");
//		String employeeId = employee.getEmployeeId();
//		String contractDate = request.getParameter("contract_date");
//		int customerId = Integer.parseInt(request.getParameter("customer_id"));
//		String[] checkedPlans = request.getParameterValues("checkbox");
		
		int contractId = Integer.parseInt(request.getParameter("contract_id"));


		// Logic実行
		DeleteContractLogic bo = new DeleteContractLogic();
		contractId = bo.execute(contractId);

		// 実行後処理
		if (contractId == FAILURE) {
			request.setAttribute("errorMsg", "削除に失敗しました。");
		} else {
			request.setAttribute("resultMsg", "削除に成功しました。契約番号:" + contractId);
		}
		
//			}//★

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete_contract_result.jsp");
		dispatcher.forward(request, response);

	}

}
