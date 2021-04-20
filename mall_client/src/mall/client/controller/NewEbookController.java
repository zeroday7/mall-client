package mall.client.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;

@WebServlet("/EbookCalendarController")
public class NewEbookController extends HttpServlet {
	private EbookDao ebookDao; // 이달에 신간 ebook
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ebookDao = new EbookDao();
		
		// 년/월에 매개값으로 전달되지 않으면
		Calendar dday = Calendar.getInstance();
		
		if(request.getParameter("currentYear") != null) {
			dday.set(Calendar.YEAR, Integer.parseInt(request.getParameter("currentYear")));
		}
		if(request.getParameter("currentMonth") != null) {
			dday.set(Calendar.MONTH, Integer.parseInt(request.getParameter("currentMonth"))-1);
		}
		
		int currentYear = dday.get(Calendar.YEAR);
		int currentMonth = dday.get(Calendar.MONTH) + 1;
		
		
		// 마지막 일( ex: 31, 30, 29, 28....)
		int endDay = dday.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		// 현재달의 1일의 요일
		Calendar firstDay = Calendar.getInstance();
		firstDay.set(Calendar.YEAR, currentYear);
		firstDay.set(Calendar.MONTH, currentMonth-1);
		firstDay.set(Calendar.DATE, 1);
		int firstDayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		 
		System.out.println(currentYear+" <--currentYear");
		System.out.println(currentMonth+" <--currentMonth");
		System.out.println(endDay+" <--endDay");
		System.out.println(firstDayOfWeek + " <-- 4/1 목요일(5)");
		
		
		List<Map<String, Object>> ebookListByMonth = this.ebookDao.selectEbookListByMonth(currentYear, currentMonth);
		
		// preYear, preMonth, nextYear, nextMonth
		int preYear = currentYear;
		int preMonth = currentMonth - 1;
		if (preMonth == 0) {
			preMonth = 12;
			preYear -= 1;
		}
		int nextYear = currentYear;
		int nextMonth = currentMonth + 1;
		if (nextMonth == 13) {
			nextMonth = 1;
			nextYear += 1;
		}
		
		request.setAttribute("ebookListByMonth", ebookListByMonth);
		request.setAttribute("currentYear", currentYear);
		request.setAttribute("currentMonth", currentMonth);
		request.setAttribute("endDay", endDay);
		request.setAttribute("firstDayOfWeek", firstDayOfWeek);
		
		request.setAttribute("preYear", preYear);
		request.setAttribute("preMonth", preMonth);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		
		request.getRequestDispatcher("/WEB-INF/view/ebook/ebookCalendar.jsp").forward(request, response);
	}
}
