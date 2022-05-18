package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteContractConfirmServlet")
public class DeleteContractConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//リクエストパラメータ取得
		int contractId = Integer.parseInt(request.getParameter("contract_id"));
		
		
		//リクエストスコープに保存
		request.setAttribute("contractId",contractId);
		
		// フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete_contract_confirm.jsp");
				dispatcher.forward(request, response);

	}

}
