package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp.BasicDataSource;

public class notie_insert {
	Connection conn = null;
	
	//database 정보를 읽어오는 메소드
	public notie_insert(BasicDataSource dataSource){
		try {
		this.conn = dataSource.getConnection();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public String db_insert(String nsubject, String nwriter, String npass, String ntext) throws Exception {

		String sql = "insert into notice values ('0',?,?,?,?,default)";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, nsubject);
		ps.setString(2, nwriter);
		ps.setString(3, npass);
		ps.setString(4, ntext);
		int aa = ps.executeUpdate();
		String sign = "";
		if(aa > 0) {
			sign = "ok";
		}
		else {
			sign = "no";
		}		
		this.conn.close();
		ps.close();
		return sign;
	}
	
}
