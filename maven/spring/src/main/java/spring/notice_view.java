package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.dbcp.BasicDataSource;

public class notice_view {
	Connection conn = null;
	PreparedStatement ps = null;
	
	public notice_view(BasicDataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		}
		catch(Exception e) {
				System.out.println(e);
		}
	}
	public ArrayList<String> view_data(int nidx){
		ArrayList<String> lists = new ArrayList<String>();
		
		try {
			String sql = "select * from notice where nidx=?";
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setInt(1, nidx);
			ResultSet rs = this.ps.executeQuery();
			dao da = new dao();
			
			while(rs.next()) {
				 lists.add(rs.getString("nidx"));
				 lists.add(rs.getString("nsubject"));
				 lists.add(rs.getString("nwriter"));
				 lists.add(rs.getString("npass"));
				 lists.add(rs.getString("ntext"));
				 lists.add(rs.getString("ndate"));
			}
			this.conn.close();
			this.ps.close();
		}
		catch(Exception e) {
			System.out.println("데이터 베이스 문법 오류");
		}
		
		return lists;
	}
	
	
}
