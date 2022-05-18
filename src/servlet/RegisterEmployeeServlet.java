package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NewEmployee;
import model.RegisterEmployeeLogic;

@WebServlet("/RegisterEmployeeServlet")
public class RegisterEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register_employee.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータ取得
		String employeeId = request.getParameter("employee_id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

//		System.out.println(employeeId + "," + name + "," + pass); // ★

		// Entity初期化
		NewEmployee input = new NewEmployee(employeeId, name, pass);

		// Logic実行
		RegisterEmployeeLogic bo = new RegisterEmployeeLogic();
		boolean result = bo.execute(input);

		// 実行結果に応じた処理
		if (result) {
			System.out.println("登録に成功しました。"); // ★
			request.setAttribute("resultMsg", "登録に成功しました。");
		} else {
			System.out.println("登録に失敗しました。"); // ★
			request.setAttribute("errorMsg", "登録に失敗しました。");
		}
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register_employee_result.jsp");
		dispatcher.forward(request, response);
	}

}
