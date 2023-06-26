package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;

public class notice_select {
	Connection conn = null;
	PreparedStatement ps = null;
	
	public notice_select(BasicDataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		}
		catch(Exception e) {
				System.out.println(e);
		}
	}
	
	public int total() throws Exception {	//database에 총 데이터 갯수
		String sql2 = "select count(*) as cnt from notice";
		this.ps = this.conn.prepareStatement(sql2);
		ResultSet rs = this.ps.executeQuery();
		int ea = 0;
		while(rs.next()) {
			ea = Integer.parseInt(rs.getString("cnt"));
		}
		return ea;
	}
	
	
	
	public List<ArrayList<String>> noticelist(String search, int page){
		List<ArrayList<String>> ls = new ArrayList<ArrayList<String>>();
		String sql = null;
		try {
			//sql문법
			if(search=="" || search=="null" || search==null) {
				sql = "select * from notice order by nidx desc limit ?,2";
			}
			else {
				sql = "select * from notice where nsubject like ? order by nidx desc";	
			}
			this.ps = this.conn.prepareStatement(sql);
			
			if(search!=null) {
				this.ps.setString(1, "%"+search+"%");				
			}
			else {	//페이징 번호는 숫자 자료형으로 처리 해야함
				this.ps.setInt(1, page);
			}
			ResultSet rs = this.ps.executeQuery();
			dao da = new dao();	//DAO 연결
			
			//DAO 클래스 로드	(setter) : 해당 필드값을 setter 메소드로 전달
			while(rs.next()) {
				da.setNidx(rs.getString("nidx"));
				da.setNsubject(rs.getString("nsubject"));
				da.setNwriter(rs.getString("nwriter"));
				da.setNdate(rs.getString("ndate"));
				ls.add(da.datas());	//1차배열을 2차배열화 시켜서 저장시킴
			}
			this.conn.close();
			this.ps.close();
		}
		catch(Exception e) {
			System.out.println("SQL 문법 오류!!");
		}
		return ls;	//Controller에 2차배열로 반환
	}
	
}
