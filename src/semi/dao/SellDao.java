package semi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.ReviewVo;
import semi.vo.SellVo;
import test.dbcp.DbcpBean;

public class SellDao {
	private static SellDao instance=new SellDao();
	private SellDao() {}
	public static SellDao getInstance() {
		return instance;
	}

	
/*	public ArrayList<SellVo> checkedList(String sql){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		System.out.println(sql);
		try {
			con=DbcpBean.getConn();
			//System.out.println("sql:"+sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<SellVo> list=new ArrayList<>();
			while(rs.next()) {
				SellVo vo=new SellVo(rs.getInt("sno"),rs.getInt("os"),rs.getInt("telecom"),
						rs.getInt("company"),rs.getString("loc"),rs.getInt("price"),
						rs.getString("stitle"),rs.getString("scontent"),rs.getDate("sdate"),
						rs.getInt("sgrade"),rs.getInt("shit"),rs.getInt("success"),rs.getInt("sreport"),rs.getString("id"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}	
	}*/
	
	public int updateOk(SellVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql="update sell set os=?,telecom=?,company=?,loc=?,price=?,stitle=?,scontent=?,sdate=sysdate,success=? where sno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getOs());
			pstmt.setInt(2, vo.getTelecom());
			pstmt.setInt(3, vo.getCompany());
			pstmt.setString(4, vo.getLoc());
			pstmt.setInt(5, vo.getPrice());
			pstmt.setString(6, vo.getStitle());
			pstmt.setString(7, vo.getScontent());
			pstmt.setInt(8, vo.getSuccess());
			pstmt.setInt(9, vo.getSno());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public SellVo update(int sno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql= "select * from sell where sno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int os=rs.getInt("os");
				int telecom=rs.getInt("telecom");
				int company=rs.getInt("company");
				String loc=rs.getString("loc");
				int price=rs.getInt("price");
				String stitle=rs.getString("stitle");
				String scontent=rs.getString("scontent");
				Date sdate=rs.getDate("sdate");
				int shit=rs.getInt("shit");
				int success=rs.getInt("success");
				String id=rs.getString("id");
				SellVo vo=new SellVo(sno, os, telecom, company, loc, price, stitle, scontent, sdate, 0, shit, success, 0, id);
				return vo;
			}
		return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public SellVo detail(int sno) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select * from sell where sno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int os=rs.getInt("os");
				int telecom=rs.getInt("telecom");
				int company=rs.getInt("company");
				String loc=rs.getString("loc");
				int price=rs.getInt("price");
				String stitle=rs.getString("stitle");
				String scontent=rs.getString("scontent");
				Date sdate=rs.getDate("sdate");
				int shit=rs.getInt("shit");
				int success=rs.getInt("success");
				String id=rs.getString("id");
				
				SellVo vo=new SellVo(sno, os, telecom, company, loc, price, stitle, scontent, sdate, 0, shit, success, 0, id);
				return vo;
			}else {
				return null;
			}
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}	
	}
	public int updateHit(SellVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update sell set shit=shit+1 where sno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getSno());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int updateReport(int sno) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update sell set sreport=sreport+1 where sno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int delete(int sno, String id) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2= null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql1 = "delete from scomment where sno=?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, sno);
			int n1 = pstmt1.executeUpdate();
			String sql2 = "delete from sell where sno=? and id=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, sno);
			pstmt2.setString(2, id);
			int n2 = pstmt2.executeUpdate();
			return n2; 
	}catch(SQLException se) {
		System.out.println(se.getMessage());
		return -1;
	}finally {
		DbcpBean.closeConn(con, pstmt1, rs);
		DbcpBean.closeConn(null, pstmt2, null);
	}	
}
	
	public int insert(SellVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="insert into sell values(sell_seq.nextval,?,?,?,?,?,?,?,sysdate,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getOs());
			pstmt.setInt(2, vo.getTelecom());
			pstmt.setInt(3, vo.getCompany());
			pstmt.setString(4, vo.getLoc());
			pstmt.setInt(5, vo.getPrice());
			pstmt.setString(6, vo.getStitle());
			pstmt.setString(7, vo.getScontent());
			pstmt.setInt(8, vo.getSgrade());
			pstmt.setInt(9, vo.getShit());
			pstmt.setInt(10, vo.getSuccess());
			pstmt.setInt(11, vo.getSreport());
			pstmt.setString(12, vo.getId());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select NVL(count(sno),0) cnt from sell";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt("cnt");
			//System.out.println("cnt:" + cnt);
			return cnt;
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public int getCount(String sql) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//System.out.println("sqlsql"+sql);
		
		if(sql!="") {
			sql="where " + sql;
		}else {
			sql="";
		}
		//System.out.println("Sql:" + sql);
		try {
			con=DbcpBean.getConn();
			String sql1="select NVL(count(sno),0) cnt from sell "+sql;
			//System.out.println("sqldao:"+sql1);
			pstmt=con.prepareStatement(sql1);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt("cnt");

		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public int getCount(String select ,String text) {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(select.equals("0")) {
            sqlplus=" where stitle like '%"+text+"%'";
        }else if(select.equals("1")) {
            sqlplus=" where scontent like '%"+text+"%'";
        }else if(select.equals("2")) {
            sqlplus=" where id like '%"+text+"%'";
        }
        try {
            con=DbcpBean.getConn();
            String sql="select NVL(count(sno),0) cnt from sell"+sqlplus;
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            rs.next();
            int cnt=rs.getInt("cnt");
            return cnt;
            
        }catch(SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        }finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
	public ArrayList<SellVo> list(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			//System.out.println("con:"+con);
			String sql="select * from (select aa.*,rownum rnum from(select * from sell order by sgrade desc ,sno desc)aa ) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<SellVo> list=new ArrayList<>();
			while(rs.next()) {
				SellVo vo=new SellVo(rs.getInt("sno"),rs.getInt("os"),rs.getInt("telecom"),
						rs.getInt("company"),rs.getString("loc"),rs.getInt("price"),
						rs.getString("stitle"),rs.getString("scontent"),rs.getDate("sdate"),
						rs.getInt("sgrade"),rs.getInt("shit"),rs.getInt("success"),rs.getInt("sreport"),rs.getString("id"));
				list.add(vo);
			}
			//System.out.println("list:" + list);
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	public ArrayList<SellVo> searchlist(String select ,String text,int startRow,int endRow,String chk){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        //System.out.println("select : "+select);
        if(text!=null) {
	        if(select.equals("0")) {
	            sqlplus=" where stitle like '%"+text+"%'";
	        }else if(select.equals("1")) {
	            sqlplus=" where scontent like '%"+text+"%'";
	        }else if(select.equals("2")) {
	            sqlplus=" where id like '%"+text+"%'";
	        }
        }
       // System.out.println("text : "+text);
        if(text==null && (chk!=null && !chk.equals(""))) {
        	sqlplus = " where "+chk;
        }else if(text!=null && (chk!=null && chk.equals(""))) {
        	sqlplus += " and "+chk;
        }
        //System.out.println("sqlplus:" + sqlplus);
        try {
            con=DbcpBean.getConn();
            //System.out.println("con:"+con);
            String sql="select * from (select aa.*,rownum rnum from(select * from sell"+sqlplus+" order by sno desc)aa ) where rnum>=? and rnum<=?";
            System.out.println(sql);
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<SellVo> list=new ArrayList<>();
            while(rs.next()) {
                SellVo vo=new SellVo(rs.getInt("sno"),rs.getInt("os"),rs.getInt("telecom"),
                        rs.getInt("company"),rs.getString("loc"),rs.getInt("price"),
                        rs.getString("stitle"),rs.getString("scontent"),rs.getDate("sdate"),
                        rs.getInt("sgrade"),rs.getInt("shit"),rs.getInt("success"),rs.getInt("sreport"),rs.getString("id"));
                list.add(vo);
            }
            return list;
        }catch(SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }

	public int getPolice(int sno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DbcpBean.getConn();
			String sql= "select count(*) from report where type='sell' and bonum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int count=rs.getInt("count(*)");
				return count;
			}
			return -1;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(conn, pstmt, rs);
		}
	}
	
	public int police(int sno,String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DbcpBean.getConn();
			String sql="insert into report values(report_seq.nextval,'sell',?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(conn, pstmt, null);
		}
	}
	

	
	public int oxpolice(int sno,String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DbcpBean.getConn();
			String sql="select count(*) cnt from report where id=? and type='sell' and bonum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int count=rs.getInt("cnt");
				return count;
			}
		return -1;
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(conn, pstmt, rs);
		}
	}
	
	public ArrayList<SellVo> sellBatch(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select * from sell where TO_DATE(TO_CHAR(SYSDATE-1,'YYYY/MM/DD'))<=sdate and SDATE<TO_DATE(TO_CHAR(SYSDATE,'YYYY/MM/DD')) AND SUCCESS=2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<SellVo> batchList=new ArrayList<>();
			while(rs.next()) {
				SellVo vo=new SellVo(rs.getInt("sno"),rs.getInt("os"),rs.getInt("telecom"),
						rs.getInt("company"),rs.getString("loc"),rs.getInt("price"),
						rs.getString("stitle"),rs.getString("scontent"),rs.getDate("sdate"),
						rs.getInt("sgrade"),rs.getInt("shit"),rs.getInt("success"),rs.getInt("sreport"),rs.getString("id"));
				batchList.add(vo);
			}
			return batchList;
			
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}

}
