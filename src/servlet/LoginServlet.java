package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ログインに失敗時に「戻る」を押した時のフォワード先
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// リクエストパラメータの取得
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		// employeeインスタンス（ユーザー情報）の生成
		Login login = new Login(id, pass);

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		Employee employee = loginLogic.execute(login);

		// 実行後処理
		if (employee != null) {// ログイン成功時
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);

		} else if (employee == null) {// ログイン失敗時
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login_failure.jsp");
			dispatcher.forward(request, response);
		}
	}
}
