package spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/* 
  Controller 인터페이스를 로드함 
  Override를 이용하여 해당 객체를 로드 합니다.
*/

public class index implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String mid = request.getParameter("userid");
		String muser = request.getParameter("username");
		
		System.out.println(mid);
		System.out.println(muser);
		ModelAndView mv = new ModelAndView("index");
		//addObject : setAttribute와 동일한 기능
		request.setAttribute("muser", muser);	//attr형식의 <%=%>
		
		//표현식 ${}형태로 출력
		mv.addObject("data", "스프링 데이터 출력 확인!!");
		mv.addObject("userlist","사용자 리스트 력!!");
		
		//setViewName : 출력할 jsp 파일명을 적용
		//mv.setViewName("./index.jsp");
		return mv;
	}
	
}
