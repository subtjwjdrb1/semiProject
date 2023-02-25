package semi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.BuyVo;
import semi.vo.FqboardVo;
import semi.vo.MemberVo;
import semi.vo.SellVo;
import test.dbcp.DbcpBean;

public class BuyDao {
	
	private static BuyDao instance=new BuyDao();
	private BuyDao() {}
	public static BuyDao getInstance() {
		return instance;
	}
	
	public BuyVo update(int bno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql= "select * from buy where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Date bdate=rs.getDate("bdate");
				int bhit=rs.getInt("bhit");
				int success=rs.getInt("success");
				String id=rs.getString("id");
				//System.out.println("success?????"+success);
				BuyVo vo=new BuyVo(bno, btitle, bcontent, bdate, 0, bhit, success, 0, id);
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
	
	public int updateOk(BuyVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql="update buy set btitle=?,bcontent=?, success=? where bno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setInt(3, vo.getSuccess());
			pstmt.setInt(4, vo.getBno());
			//System.out.println("제목:"+ vo.getBtitle() + "거래상황:"+vo.getSuccess());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int updateHit(BuyVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update buy set bhit=bhit+1 where bno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBno());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int updateReport(int bno) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update buy set breport=breport+1 where bno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int delete(int bno, String id) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2= null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql1 = "delete from bcomment where bno=?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, bno);
			int n1 = pstmt1.executeUpdate();
			String sql2 = "delete from buy where bno=? and id=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, bno);
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
	
	public BuyVo detail(int bno) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select * from buy where bno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String btitle=rs.getString("btitle");
				String bcontent=rs.getString("bcontent");
				Date bdate=rs.getDate("bdate");
				int bhit=rs.getInt("bhit");
				int success=rs.getInt("success");
				String id=rs.getString("id");
				
				BuyVo vo=new BuyVo(bno, btitle, bcontent, bdate, 0, bhit, success, 0, id);
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
	
	public int insert(BuyVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="insert into buy values(buy_seq.nextval,?,?,sysdate,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setInt(3, vo.getBgrade());
			pstmt.setInt(4, vo.getBhit());
			pstmt.setInt(5, vo.getSuccess());
			pstmt.setInt(6, vo.getBreport());
			pstmt.setString(7, vo.getId());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int getCount(String select ,String text){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(select.equals("0")) {
            sqlplus="where btitle like'%"+text+"%'";
        }else if(select.equals("1")) {
            sqlplus="where bcontent like'%"+text+"%'";
        }else if(select.equals("2")) {
            sqlplus="where id like'%"+text+"%'";
        }
        try {
            con=DbcpBean.getConn();
            String sql= "select count(bno) cnt from buy "+sqlplus;
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
	
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select NVL(count(bno),0) cnt from buy";
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
	
	public ArrayList<BuyVo> search(String select ,String text,int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(select.equals("0")) {
            sqlplus=" where btitle like '%"+text+"%' ";
        }else if(select.equals("1")) {
            sqlplus=" where bcontent like '%"+text+"%' ";
        }else if(select.equals("2")) {
            sqlplus=" where id like '%"+text+"%' ";
        }
        try {
            con=DbcpBean.getConn();
            String sql= "select * from(" + 
                    "  select aa.*,rownum rnum from (" + 
                    "   select * from buy "+sqlplus+
                    "        order by bgrade desc, bno desc" + 
                    "   )aa" + 
                    ") where rnum>=? and rnum<=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<BuyVo> list =new  ArrayList<BuyVo>();
            while(rs.next()) {
                
                list.add(new BuyVo(rs.getInt("bno"), rs.getString("btitle"), 
                		rs.getString("bcontent"), rs.getDate("bdate"), rs.getInt("bgrade"), 
                		rs.getInt("bhit"), rs.getInt("success"), rs.getInt("breport"), 
                		rs.getString("id")));
            }
            return list;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
	
	public ArrayList<BuyVo> list(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			//System.out.println("con:"+con);
			String sql="select * from (select aa.*,rownum rnum from(select * from buy order by bgrade desc ,bno desc)aa ) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<BuyVo> list=new ArrayList<>();
			while(rs.next()) {
				BuyVo vo=new BuyVo(rs.getInt("bno"), rs.getString("btitle"), rs.getString("bcontent"), 
						rs.getDate("bdate"), rs.getInt("bgrade"), rs.getInt("bhit"), rs.getInt("success"), 
						rs.getInt("breport"), rs.getString("id"));
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
	
	public int getPolice(int bno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql= "select count(*) from report where type='buy' and bonum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
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
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}
	
	public int police(int bno,String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbcpBean.getConn();
			String sql="insert into report values(report_seq.nextval,'buy',?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int oxpolice(int bno,String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql="select count(*) cnt from report where id=? and type='buy' and bonum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, bno);
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
			DbcpBean.closeConn(con, pstmt, rs);
		}
	}

}
