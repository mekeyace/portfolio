package spring;

import java.sql.Connection;
import java.sql.DriverManager;

//데이터 베이스 환경 설정 Module
public class dbconfig {
	
	public static Connection info() throws Exception{
		//String db_driver = "com.mysql.cj.jdbc.Driver";
		String db_driver = "com.mysql.cj.jdbc.Driver";
		//String db_url = "jdbc:mysql://umj7-009.cafe24.com/leejongh";
		String db_url = "jdbc:mysql://localhost/leejongh";
		String db_user = "leejongh";
		String db_pass = "Tt04860486!";

		Class.forName(db_driver);
		Connection con = DriverManager.getConnection(db_url,db_user,db_pass);
		return con;
	}
	
}
