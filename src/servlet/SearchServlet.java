package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Condition;
import model.Employee;
import model.Result;
import model.SearchLogic;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータ取得(画面に入力された値をとってくる)
		String start = null;
		String end = null;
		String customer = null;
		String plan = null;

		start = request.getParameter("start_date");
		end = request.getParameter("end_date");
		customer = request.getParameter("customer_name");
		plan = request.getParameter("plan_name");

		// セッションスコープからログインユーザー情報取得
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		String employeeId = employee.getEmployeeId(); // 本来はこのコードを使用

		// Entity初期化
		Condition input = new Condition(start, end, customer, plan, employeeId);

		// Logic実行
		SearchLogic bo = new SearchLogic();
		List<Result> table = bo.execute(input);

		// 実行結果に応じた処理
		if (table == null) {
			System.out.println("条件に一致するものが見つかりませんでした。"); // ★
			request.setAttribute("errorMsg", "条件に一致するものが見つかりませんでした。");

		} else {
			request.setAttribute("table", table); // リクエストスコープにインスタンスを保存
		}

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search_result.jsp");
		dispatcher.forward(request, response);
	}

}
