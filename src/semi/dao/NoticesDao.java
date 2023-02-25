package semi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.NoticesVo;
import test.dbcp.DbcpBean;

public class NoticesDao {
    private static NoticesDao instance=new NoticesDao();
    private NoticesDao() {}
    public static NoticesDao getInstance() {
        return instance;
    }
    public int update(NoticesVo vo) {
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            String sql="update notices set title=?,content=? where num=?";
            con=DbcpBean.getConn();
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setInt(3, vo.getNum());
            int n = pstmt.executeUpdate();
            return n;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        }finally {
            DbcpBean.closeConn(con, pstmt, null);
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
    public NoticesVo detail(int num1) {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select * from notices where num=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, num1);
            rs=pstmt.executeQuery();
            if(rs.next()) {
                int num=rs.getInt("num");
                String title=rs.getString("title");
                String content=rs.getString("content");
                int hit = rs.getInt("hit");
                Date ndate = rs.getDate("ndate");
                NoticesVo vo=new NoticesVo(num, title, content, hit, ndate);
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
    public int updateHit(NoticesVo vo) {
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            con=DbcpBean.getConn();
            String sql="update notices set hit=hit+1 where num=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, vo.getNum());
            return pstmt.executeUpdate();
        }catch(SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        }finally {
            DbcpBean.closeConn(con, pstmt, null);
        }
    }
    public int getMax(String text){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql= "select count(num) cnt from notices "+text;
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
    public int getMax() {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql = "select count(num) cnt from notices";
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
    public ArrayList<NoticesVo> noticesList(String text,int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="";
        String sqlplus="where title like '%"+text+"%'";
        try {
            con=DbcpBean.getConn();
            if(text==null||text=="") {
                sql="select * from (select aa.*,rownum rnum from(select * from notices order by num desc)aa ) where rnum>=? and rnum<=?";
            }else {
                sql="select * from (select aa.*,rownum rnum from(select * from notices "+sqlplus+" order by num desc)aa ) where rnum>=? and rnum<=?";
            }
            System.out.println("sql : "+sql);
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<NoticesVo> list=new ArrayList<>();
            while(rs.next()) {
                NoticesVo vo=new NoticesVo(rs.getInt("num"),rs.getString("title"),rs.getString("content"),rs.getInt("hit"),rs.getDate("ndate"));
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
    public ArrayList<NoticesVo> noticesStart(){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select * from (select aa.*,rownum rnum from(select * from notices order by num desc)aa ) where rnum>=1 and rnum<=6";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            ArrayList<NoticesVo> list=new ArrayList<>();
            while(rs.next()) {
                NoticesVo vo=new NoticesVo(rs.getInt("num"),rs.getString("title"),rs.getString("content"),rs.getInt("hit"),rs.getDate("ndate"));
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
}
