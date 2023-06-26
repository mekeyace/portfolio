package plan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index1.do")
public class index1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public index1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher rd = request.getRequestDispatcher("http://");
		//request.setAttribute("data1", "홍길동");
		String data1 = "hong";
		response.setHeader("Location", "http://192.168.10.151:8080/abc.jsp?name="+data1);
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);		
	}

}
