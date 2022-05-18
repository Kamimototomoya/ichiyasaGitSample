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

/**
 * Servlet implementation class SearchDefault
 */
@WebServlet("/SearchDefaultServlet")
public class SearchDefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッションスコープからログインユーザー情報取得
		HttpSession session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		String employeeId = employee.getEmployeeId(); // 本来はこのコードを使用

		// Entity初期化
		Condition input = new Condition("", "", "", "", employeeId);

		// Logic実行
		SearchLogic bo = new SearchLogic();
		List<Result> table = bo.execute(input);

		// リクエストスコープにインスタンスを保存
		request.setAttribute("table", table);

		System.out.println("table=" + table);
		System.out.println("table.size()="+table.size());

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search_result.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
