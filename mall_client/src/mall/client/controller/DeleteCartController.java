package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.Cart;
import mall.client.vo.Client;

/**
 * Servlet implementation class DeleteCartController
 */
@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비회원 못들오게 막기
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null ) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}

		//전처리
		Cart cart = new Cart();
		cart.setEbookNo(Integer.parseInt(request.getParameter("ebookNo")));
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		//디버깅
		System.out.println(cart.toString()+"<DeleteCartController>");
		//dao불러옴
		cartDao = new CartDao();
		cartDao.deleteCart(cart);

		//redirect
		response.sendRedirect(request.getContextPath()+"/CartListController");
	}

}
