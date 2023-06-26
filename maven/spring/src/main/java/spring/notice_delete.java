package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp.BasicDataSource;

public class notice_delete {
	Connection conn = null;
	PreparedStatement ps = null;
	
	public notice_delete(BasicDataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		}
		catch(Exception e) {
				System.out.println(e);
		}
	}
	
	public String delete_ok(String nidx,String pw) {
		
		String sql = "delete from notice where nidx=? and npass=?";
		String msg = "";
		try {
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nidx);
		this.ps.setString(2, pw);
		int ck = this.ps.executeUpdate();
			if(ck > 0) {
			msg = "ok";
			}
			else {
			msg = "no";	
			}
		this.conn.close();
		this.ps.close();
		}
		catch(Exception e) {
		System.out.println("데이터 베이스 문법 오류!!");
		msg = "no";
		}
		
		return msg;
	}
}
