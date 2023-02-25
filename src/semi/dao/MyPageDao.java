package semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.MyPageVo;
import test.dbcp.DbcpBean;

public class MyPageDao {
	private static MyPageDao instance=new MyPageDao();
	private MyPageDao() {}
	public static MyPageDao getInstance() {
		return instance;
	}
	
	public int getCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql="select NVL(count(sno),0) cnt from scrap";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			return cnt;
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(conn, pstmt, rs);
		}
	}
	
	public int getCount(String select ,String text){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(select.equals("0")) {
            sqlplus="where title like'%"+text+"%'";
        }
        try {
            con=DbcpBean.getConn();
            String sql= "select count(sno) cnt from scrap "+sqlplus;
            pstmt = con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            rs.next();
            return rs.getInt("cnt");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
	
	public int scrapAdd(MyPageVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbcpBean.getConn();
			String sql="insert into scrap values(sno_seq.nextval,?,?,?,sysdate,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getUrl());
			pstmt.setInt(4, vo.getRno());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(conn,pstmt,null);
		}
	}
	public ArrayList<MyPageVo> reviewList(String id,String select ,String text,int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(text!=null) {
            if(select.equals("0")) {
                sqlplus="and title like '%"+text+"%'";
                }
        }
        try {
            con=DbcpBean.getConn();
            //System.out.println("con:"+con);
            String sql="select * from (select aa.*,rownum rnum from(select * from scrap where id=? "+sqlplus+" order by sno desc )aa ) where rnum>=? and rnum<=?";
            System.out.println(sql);
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            rs=pstmt.executeQuery();
            System.out.println(id+","+startRow+"," +endRow);
            ArrayList<MyPageVo> list=new ArrayList<>();
            while(rs.next()) {
                MyPageVo vo=new MyPageVo(rs.getInt("sno"),rs.getString("title"),rs.getString("id"),rs.getString("url"),rs.getDate("sdate"),rs.getInt("rno"));
                list.add(vo);
                System.out.println(rs.getString("url"));
                System.out.println(rs.getString("id"));
                System.out.println(rs.getInt("sno"));
                
            }
            return list;
        }catch(SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
	 public int delete(String sql) {
	        Connection con=null;
	        PreparedStatement pstmt=null;
	        try {
	            con=DbcpBean.getConn();
	            pstmt=con.prepareStatement(sql);
	            int n = pstmt.executeUpdate();
	            return n;
	        } catch (SQLException se) {
	            System.out.println(se.getMessage());
	            return -1;
	        }finally {
	            DbcpBean.closeConn(con, pstmt, null);
	        }
	    }
}
