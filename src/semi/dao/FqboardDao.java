package semi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.BuyVo;
import semi.vo.FqboardVo;
import semi.vo.NoticesVo;
import semi.vo.SellVo;
import test.dbcp.DbcpBean;

public class FqboardDao {
	
	private static FqboardDao instance=new FqboardDao();
	private FqboardDao () {}
	public static FqboardDao getInstance() {
		return instance;
	}
	
	public ArrayList<FqboardVo> fqmain(){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select * from (select aa.*,rownum rnum from(select * from fqboard order by fqno desc)aa ) where rnum>=1 and rnum<=6";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            ArrayList<FqboardVo> list=new ArrayList<>();
            while(rs.next()) {
                FqboardVo vo=new FqboardVo(rs.getInt("fqno"), rs.getInt("fqtype"), rs.getString("fqtitle"), rs.getString("fqcontent"), rs.getDate("fqdate"), rs.getInt("fqhit"), rs.getInt("fqgrade"), rs.getInt("fqreport"), rs.getString("id"), rs.getInt("recommend"));
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
	
	public int insert(FqboardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="insert into fqboard values(fq_seq.nextval,?,?,?,sysdate,0,0,0,?,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getFqtype());
			pstmt.setString(2, vo.getFqtitle());
			pstmt.setString(3, vo.getFqcontent());
			pstmt.setString(4, vo.getId());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int delete(int fqno, String id) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2= null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql1 = "delete from fqcomment where fqno=?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, fqno);
			int n1 = pstmt1.executeUpdate();
			String sql2 = "delete from fqboard where fqno=? and id=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, fqno);
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
	public int updateHit(FqboardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update fqboard set fqhit=fqhit+1 where fqno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getFqno());
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	public int updateReport(int fqno) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update fqboard set fqreport=fqreport+1 where fqno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, fqno);
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	public FqboardVo update(int fqno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql= "select * from fqboard where fqno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fqno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int fqtype=rs.getInt("fqtype");
				String fqtitle=rs.getString("fqtitle");
				String fqcontent=rs.getString("fqcontent");
				int fqhit=rs.getInt("fqhit");
				int fqreport=rs.getInt("fqreport");
				String id=rs.getString("id");
				int recommend=rs.getInt("recommend");
				System.out.println("DAOfqtype:"+rs.getInt("fqtype"));
				System.out.println("DAOfqtitle:"+rs.getString("fqtitle"));
				FqboardVo vo=new FqboardVo(fqno, fqtype, fqtitle, fqcontent, null, fqhit, 0, fqreport, id, recommend);
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
	public int updateOk(FqboardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=DbcpBean.getConn();
			String sql="update fqboard set fqtype=?,fqtitle=?,fqcontent=? where fqno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getFqtype());
			pstmt.setString(2, vo.getFqtitle());
			pstmt.setString(3, vo.getFqcontent());
			pstmt.setInt(4, vo.getFqno());
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
            sqlplus="where fqtitle like'%"+text+"%'";
        }else if(select.equals("1")) {
            sqlplus="where fqcontent like'%"+text+"%'";
        }else if(select.equals("2")) {
            sqlplus="where id like'%"+text+"%'";
        }
        try {
            con=DbcpBean.getConn();
            String sql= "select count(fqno) cnt from fqboard "+sqlplus;
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
			String sql="select NVL(count(fqno),0) cnt from fqboard";
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
	public ArrayList<FqboardVo> search(String select ,String text,int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(select.equals("0")) {
            sqlplus=" where fqtitle like '%"+text+"%' ";
        }else if(select.equals("1")) {
            sqlplus=" where fqcontent like '%"+text+"%' ";
        }else if(select.equals("2")) {
            sqlplus=" where id like '%"+text+"%' ";
        }
        try {
            con=DbcpBean.getConn();
            String sql= "select * from(" + 
                    "  select aa.*,rownum rnum from (" + 
                    "   select * from fqboard "+sqlplus+
                    "        order by fqgrade desc, fqno desc" + 
                    "   )aa" + 
                    ") where rnum>=? and rnum<=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<FqboardVo> list =new  ArrayList<>();
            while(rs.next()) {
                list.add(new FqboardVo(rs.getInt("fqno"), rs.getInt("fqtype"), rs.getString("fqtitle"),
                		rs.getString("fqcontent"), rs.getDate("fqdate"), rs.getInt("fqhit"), rs.getInt("fqgrade"),
                		rs.getInt("fqreport"), rs.getString("id"), rs.getInt("recommend")));
            }
            return list;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
	
	public ArrayList<FqboardVo> list(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			//System.out.println("con:"+con);
			String sql="select * from (select aa.*,rownum rnum from(select * from fqboard order by fqgrade desc ,fqno desc)aa ) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<FqboardVo> list=new ArrayList<>();
			while(rs.next()) {
				list.add(new FqboardVo(rs.getInt("fqno"), rs.getInt("fqtype"), rs.getString("fqtitle"),
                		rs.getString("fqcontent"), rs.getDate("fqdate"), rs.getInt("fqhit"), rs.getInt("fqgrade"),
                		rs.getInt("fqreport"), rs.getString("id"), rs.getInt("recommend")));
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
	
	public FqboardVo detail(int fqno) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select * from fqboard where fqno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, fqno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int fqtype=rs.getInt("fqtype");
				String fqtitle=rs.getString("fqtitle");
				String fqcontent=rs.getString("fqcontent");
				Date fqdate=rs.getDate("fqdate");
				int fqhit=rs.getInt("fqhit");
				int fqreport=rs.getInt("fqreport");
				int recommend=rs.getInt("recommend");
				String id=rs.getString("id");
				FqboardVo vo=new FqboardVo(fqno, fqtype, fqtitle, fqcontent, fqdate, fqhit, 0, fqreport, id, recommend);
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
	
	public int getPolice(int fqno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql= "select count(*) from report where type='fq' and bonum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fqno);
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
	
	public int police(int fqno,String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbcpBean.getConn();
			String sql="insert into report values(fq_seq.nextval,'fq',?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fqno);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int oxpolice(int fqno,String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql="select count(*) cnt from report where id=? and type='fq' and bonum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, fqno);
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
	
	public int getRecommend(int fqno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=DbcpBean.getConn();
			String sql= "select count(*) from recommend where type='fq' and bonum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fqno);
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
	
	public int recommend(int fqno,String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = DbcpBean.getConn();
			String sql="insert into recommend values(fqre_seq.nextval,'fq',?,?)";
			String sql2="update fqboard set recommend=recommend+1 where fqno=?";
			pstmt = con.prepareStatement(sql);
			pstmt2=con.prepareStatement(sql2);
			pstmt.setInt(1, fqno);
			pstmt.setString(2, id);
			pstmt2.setInt(1, fqno);
			pstmt2.executeQuery();
			return pstmt.executeUpdate();
		
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
	public int oxrecommend(int fqno,String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//System.out.println("fqno:"+fqno+"id:"+id);
		try {
			conn=DbcpBean.getConn();
			String sql="select count(*) cnt from recommend where id=? and type='fq' and bonum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, fqno);
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
	
}
