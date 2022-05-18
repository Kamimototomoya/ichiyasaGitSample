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

@WebFilter({ "/RegisterEmployeeServlet"})

public class AuthorityFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		Employee employee = (Employee) session.getAttribute("employee");

		 if(employee.getEmployeeId().startsWith("ad")){
			 chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("/MainServlet");
		}
	}

	public void destroy() {
	}

}
