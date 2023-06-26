package web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class api {

	@Autowired
	BasicDataSource dataSource;
	
	@RequestMapping("api.do")
	public void apiserver(Model m, HttpServletRequest req) {
		
		String result = "";	//배열 데이터를 최종적으로 이용되는 변수
		
		//Front-end 넘어오는 api key값
		String apikey = req.getParameter("keys").intern();			
		
		//DB호출
		try {
			if(apikey == "notice_oksign"){
			Connection con = dataSource.getConnection();
			String sql = "select * from notice order by nidx desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			//JSON 배열생성
			JSONArray ja = new JSONArray();
			while(rs.next()) {
				//JSON 키 생성 및 데이터 삽입
				JSONObject jb = new JSONObject();
				jb.put("nidx",rs.getString("nidx"));
				jb.put("subject",rs.getString("nsubject"));
				jb.put("writer",rs.getString("nwriter"));
				jb.put("texts",rs.getString("ntext"));
				jb.put("indate",rs.getString("ndate"));
				ja.add(jb);
			}
			result = ja.toString();	//웹에 문자열로 출력시킴
			m.addAttribute("result",result);	
			con.close();
			ps.close();
			}	//if문
			else {
				//apikey가 맞지 않을 경우
				m.addAttribute("result","error:32");	
			}
		}
		catch(Exception e) {
			//DB 문법 오류 발생
			m.addAttribute("result","error:00");
		}
		
		
	}
	
}
