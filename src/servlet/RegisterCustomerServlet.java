package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NewCustomer;
import model.RegisterCustomerLogic;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int FAILURE = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register_customer.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータ取得
		String name = request.getParameter("customer_name");
		String registrationDate = request.getParameter("registration_date");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		// Entity初期化
		NewCustomer input = new NewCustomer(name, registrationDate, age, address, tel);

		// Logic実行
		RegisterCustomerLogic bo = new RegisterCustomerLogic();
		int customerId = bo.execute(input);

		// 実行後処理
		if (customerId == FAILURE) {
			request.setAttribute("errorMsg", "登録に失敗しました。");
		} else {
			request.setAttribute("resultMsg", "登録に成功しました。契約者番号:" + customerId);
		}
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register_customer_result.jsp");
		dispatcher.forward(request, response);

	}

}
