package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Detail;
import model.ShowCustomerLogic;
import model.ShowDetailLogic;

@WebServlet("/ShowDetailServlet")
public class ShowDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータ取得
		int contractId = Integer.parseInt(request.getParameter("contract_id"));

		// Logic実行
		ShowDetailLogic bo1 = new ShowDetailLogic();
		List<Detail> detailList = bo1.execute(contractId); // ShowDetaiLogic実行

		int customerId = detailList.get(0).getCustomerId();

		ShowCustomerLogic bo2 = new ShowCustomerLogic();
		List<Customer> customerList = bo2.execute(customerId); // ShowCustomerLogic実行

		// 実行後処理
		request.setAttribute("detailList", detailList); // detailListをリクエストスコープに保存
		request.setAttribute("customerList", customerList); // customerListをリクエストスコープに保存

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/show_detail.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
