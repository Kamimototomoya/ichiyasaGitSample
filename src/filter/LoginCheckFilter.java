package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;

@WebFilter({ "/LogoutServlet", "/RegisterContractServlet", "/RegisterCustomerServlet", "/RegisterEmployeeServlet",
		"/SearchServlet", "/ShowCustomerServlet", "/ShowDetailServlet", "/ShowPlanServlet" })

public class LoginCheckFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		if (employee == null) {
			((HttpServletResponse) response).sendRedirect("/LoginServlet");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

}
