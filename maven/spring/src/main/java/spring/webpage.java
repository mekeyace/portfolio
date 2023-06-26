package spring;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//@ : 모든 클래스 및 메소드에 적용하는 전역형태의 코드
@Controller
public class webpage {
	
	//@RequestMapping : 연결고리 설정 코드
	@RequestMapping("/loginok.do")	
	/*
	가상 파일명 단, html과 동일한 이름을 .do 없이 사용시 우선순위는
	.jsp가 우선순위가 적용됨
	*/
	/*  //Model + addAttribute
	public void loginok(HttpServletRequest req, Model m) {
		String mid = req.getParameter("mid");
		String mpass = req.getParameter("mpass");
		//addAttribute : 동적코드 (setAttribute + getAttribute)
		m.addAttribute("userid",mid);
		m.addAttribute("userpw",mpass);
	}
	*/
	
	//ModelAndView + addObject = return
	public ModelAndView loginok(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String mid = req.getParameter("mid");
		String mpass = req.getParameter("mpass");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("userid",mid);
		mv.addObject("userpw",mpass);
	
		return mv;
	}
			
	//@RequestMapping("/joinok.do")
	/*
	@PostMapping : POST전송
	@GetMapping : GET전송
	@PutMapping : PUT전송 (Database - insert)
	@DeleteMapping : Delete전송 (Database - delete)
	@PatchMapping : Patch전송 (Database - update)
	*/
	@GetMapping("/joinok.do")
	public String joinok(HttpServletRequest req, Model m) {
		
		String search = req.getParameter("search");
		m.addAttribute("search",search);
		
		return "joinok";
	}
		
	@PostMapping("radio.do")
	public String radio(HttpServletRequest req, Model m) {
		String front = req.getParameter("season");
		//module 로드함
		season se = new season();
		//module에서 return값 전달
		int result = se.data(front);
		m.addAttribute("total",result);		
		
		return "status";
	}
	
	//파일 업로드
	@RequestMapping("fileok.do")
	//MultipartFile : 첨부파일 관련 사항을 로드하여 사용함 (Part X)
	public void upload(MultipartFile mfile, HttpServletRequest req, Model m) throws Exception {
		//파일이름
		String filename = mfile.getOriginalFilename();
		//파일 사이즈
		long filesize = mfile.getSize();
		//저장경로 (웹 경로)
		String url = req.getServletContext().getRealPath("") + "image/" + filename;
		
		//파일저장
		FileCopyUtils.copy(mfile.getBytes(), new File(url));
		
		//view에 해당 파일을 출력
		m.addAttribute("filename", filename);
		m.addAttribute("filesize", filesize);
	}	
	
	@PostMapping("pensionok.do")
	public void pension(HttpServletRequest req, Model m) {
		try {
			String p_area = req.getParameter("p_area");
			String p_name = req.getParameter("p_name");
			String p_reserv = req.getParameter("p_reserv");
			String p_email = req.getParameter("p_email");
			int p_person = Integer.parseInt(req.getParameter("p_person"));
			
			dbconfig db = new dbconfig();
			Connection con = db.info();
			String sql = "insert into pension2 values ('0',?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p_area);
			ps.setString(2, p_name);
			ps.setString(3, p_reserv);
			ps.setString(4, p_email);
			ps.setInt(5, p_person);
			int call = ps.executeUpdate();
			if(call > 0) {
				m.addAttribute("message","정상적으로 등록 완료 되었습니다.");
			}
			else {
				m.addAttribute("message","예약시스템 오류가 발생 하였습니다.");
			}
			con.close();
			ps.close();
		}
		catch(Exception e) {
			System.out.println("DB Connect Error!!");
		}
	}
	@Autowired	//xml에 등록된 bean에 대한 id값을 가져올 때 사용
	BasicDataSource dataSource;	//xml에 대한 SQL 정보를 가져옴
	
	//게시판 삭제
	@PostMapping("notice_delete.do")
	public void delete_notice(HttpServletRequest req, Model m) {
		String nidx = req.getParameter("nidx");	//자동증가값
		String pw = req.getParameter("npass");	//사용자가 입력한 게시물 패스워드
		
		notice_delete nd = new notice_delete(dataSource);
		String result = nd.delete_ok(nidx,pw);
		m.addAttribute("result",result);
	}
	
	
	//게시판 읽기
	@GetMapping("notice_view.do")
	/*
	javascript를 Controller에서 사용시 response가 필요함, 단 해당 문법을 사용시
	forword를 미적용 하게 되면, 절대 notice_view.jsp를 로드 하지 못함
	*/
	public void notice_v(HttpServletRequest req, Model m){
		try {
			int nidx = Integer.valueOf(req.getParameter("nidx"));
			notice_view nv = new notice_view(dataSource);
			ArrayList<String> as = nv.view_data(nidx);
			m.addAttribute("notice_data",as);
		}
		catch(NumberFormatException e) {
			m.addAttribute("nidx","error");
		}
	}
	
	
	
	
	//게시판 리스트 (select - DAO, DTO, VO) -> RestAPI or Restful-API
	@GetMapping("notice_list.do")
	public void notice_l(HttpServletRequest req, Model m) {
			//검색어 부분
			String search = req.getParameter("search");		
			String p = req.getParameter("page");
			int page = 0;
			int vpage = 0;
			
			if(p==null || p=="null" || p.equals("1") || p=="") {
				page = 0;
				vpage = 1;
			}
			else {
				page = (Integer.parseInt(p) * 2) - 2;
				vpage = Integer.parseInt(p);
			}		
			try {
				//Database 문법사용 및 데이터확인 class
				notice_select ns = new notice_select(dataSource);
				
				//page전체 갯수 return 코드 (database close보다는 위에 선언함)
				int totals = ns.total();
				
				//DAO,DTO,VO에 관련된 배열값을 return 받는 코드
				List<ArrayList<String>> nlist = ns.noticelist(search,page);
				
				req.setAttribute("totals", totals);
				req.setAttribute("now_page", vpage);
				//notice_list.jsp로 데이터를 보냄 (view로 전달)
				m.addAttribute("noticelist",nlist);
			}
			catch(Exception e) {
				System.out.println("데이터 오류 발생!!");
			}
	}
		
	//게시판 글쓰기
	@PostMapping("notice_writeok.do")
	public void notice_w(HttpServletRequest req, HttpServletResponse res) {
		String nsubject = req.getParameter("nsubject");
		String nwriter = req.getParameter("nwriter");
		String npass = req.getParameter("npass");
		String ntext = req.getParameter("ntext");
		try {
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		PrintWriter pw = res.getWriter();
		String result = new notie_insert(dataSource).db_insert(nsubject, nwriter, npass, ntext);
		if(result.equals("ok")) {
			pw.print("<script>"
					+ "alert('게시물이 정상적으로 등록 되었습니다.');"
					+ "location.href='#';"
					+ "</script>");
		}
		else {
			pw.print("<script>"
					+ "alert('접속 오류 인하여 등록되지 않았습니다.');"
					+ "history.go(-1)1;"
					+ "</script>");
		}
		}
		catch(Exception e) {
			System.out.println("DB Connect Error!!");
		}
		
	}
	
	
	
	
}
