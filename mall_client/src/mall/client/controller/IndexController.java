package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;
import mall.client.model.OrdersDao;
import mall.client.vo.Ebook;

// C -> M -> V
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private EbookDao ebookDao;
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 분석
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 15;
		int beginRow = (currentPage-1)*rowPerPage;
		System.out.printf("curretnPage:%d, beginRow:%d", currentPage, beginRow);
		// model 호출
		// 베스트 ebook
		this.ordersDao = new OrdersDao();
		List<Map<String, Object>> bestOrdersList = this.ordersDao.selectBestOrdersList();
		// 전체 ebook
		this.ebookDao = new EbookDao();
		List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage);
		
		// View forward
		request.setAttribute("bestOrdersList", bestOrdersList);
		request.setAttribute("ebookList", ebookList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
}
