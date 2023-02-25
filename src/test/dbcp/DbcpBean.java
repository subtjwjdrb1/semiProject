package test.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DbcpBean {
	private static DataSource ds;
	
	static{
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
			System.out.println(ne.getMessage());
		}
	}
	
	public static Connection getConn() throws SQLException {
		Connection con=ds.getConnection();
		return con;
	}
	
	public static void closeConn(Connection con) {
		try {
			if(con!=null) con.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			DbcpBean.closeConn(con);
		}
	}
	public static void closeConn(Connection con,Statement pstmt,ResultSet rs) {
		try {
			if(con!=null) con.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}