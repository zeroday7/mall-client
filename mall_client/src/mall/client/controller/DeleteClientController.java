package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.ClientDao;
import mall.client.vo.Client;

/**
 * Servlet implementation class DeleteClientController
 */
@WebServlet("/DeleteClientController")
public class DeleteClientController extends HttpServlet {
	private ClientDao clientDao;
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원인지 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		//session에서 회원 메일 받음
		String clientMail = ((Client)session.getAttribute("loginClient")).getClientMail();
		System.out.printf("clientMail: %s<DeleteClientController>\n",clientMail);
		//cartDao.deleteCartByClient()  - 회원 카트 정보 삭제
		cartDao = new CartDao();
		cartDao.deleteCartByClient(clientMail);
		//ClientDao.deleteClient() - 회원 탈퇴
		clientDao = new ClientDao();
		clientDao.deleteClient(clientMail);

		session.invalidate();

		//sendRedirect로 index돌아가기
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}
