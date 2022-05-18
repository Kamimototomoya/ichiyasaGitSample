package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Plan;
import model.ShowPlanLogic;

@WebServlet("/ShowPlanServlet")
public class ShowPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// Logic実行
		ShowPlanLogic bo = new ShowPlanLogic();
		List<Plan> table = bo.execute();

		// リクエストスコープに保存
		request.setAttribute("table", table);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/show_plan.jsp");
		dispatcher.forward(request, response);

	}
}
