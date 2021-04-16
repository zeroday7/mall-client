package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.OrdersDao;
import mall.client.vo.Client;

@WebServlet("/OrdersListController")
public class OrdersListController extends HttpServlet {
	private OrdersDao orderDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		this.orderDao = new OrdersDao();
		
		Client client = (Client)session.getAttribute("loginClient");
		List<Map<String, Object>> ordersList = this.orderDao.selectOrderListByClient(client.getClientNo());
		request.setAttribute("ordersList", ordersList);
		
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersList.jsp").forward(request, response);
	}

}










