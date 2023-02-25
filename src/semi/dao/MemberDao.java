package semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.MemberVo;
import test.dbcp.DbcpBean;

public class MemberDao {
    private static MemberDao instance=new MemberDao();
    private MemberDao() {}
    public static MemberDao getInstance() {
        return instance;
    }
    public int delete(String sql) {
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            con=DbcpBean.getConn();
            System.out.println("1");
            pstmt=con.prepareStatement(sql);
            System.out.println("2");
            int n = pstmt.executeUpdate();
            System.out.println("3");
            return n;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        }finally {
            DbcpBean.closeConn(con, pstmt, null);
        }
    }
    
    public ArrayList<MemberVo>searchMember(String select ,String text,int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(select.equals("0")) {
            sqlplus="where id like'%"+text+"%'";
        }else if(select.equals("1")) {
            sqlplus="where name like'%"+text+"%'";
        }else if(select.equals("2")) {
            sqlplus="where email like'%"+text+"%'";
        }
        try {
            con=DbcpBean.getConn();
            String sql= "select * from(" + 
                    "  select aa.*,rownum rnum from (" + 
                    "   select * from member "+sqlplus+
                    "        order by regdate desc" + 
                    "   )aa" + 
                    ") where rnum>=? and rnum<=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<MemberVo> list =new  ArrayList<MemberVo>();
            while(rs.next()) {
                list.add(new MemberVo(rs.getString("id"),rs.getString("pwd"),
                        rs.getString("nickname"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("email"),rs.getDate("regdate")));
            }
            return list;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
    
    public ArrayList<MemberVo>updateMember(String id){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
    
        try {
            con=DbcpBean.getConn();
            String sql= "select * from member where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs=pstmt.executeQuery();
            ArrayList<MemberVo> list =new  ArrayList<MemberVo>();
            while(rs.next()) {
                list.add(new MemberVo(rs.getString("id"),rs.getString("pwd"),
                        rs.getString("nickname"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("email"),rs.getDate("regdate")));
            }
            return list;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
    
    public int getMax(String select ,String text){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sqlplus="";
        if(select.equals("0")) {
            sqlplus="where id like'%"+text+"%'";
        }else if(select.equals("1")) {
            sqlplus="where name like'%"+text+"%'";
        }else if(select.equals("2")) {
            sqlplus="where email like'%"+text+"%'";
        }
        try {
            con=DbcpBean.getConn();
            String sql= "select count(id) cnt from member "+sqlplus;
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
            String sql = "select count(id) cnt from member";
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
    public ArrayList<MemberVo> selectAll(int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql= "select * from(" + 
                    "  select aa.*,rownum rnum from (" + 
                    "   select * from member" + 
                    "        order by regdate desc" + 
                    "   )aa" + 
                    ") where rnum>=? and rnum<=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<MemberVo> list =new  ArrayList<MemberVo>();
            while(rs.next()) {
                list.add(new MemberVo(rs.getString("id"),rs.getString("pwd"),
                        rs.getString("nickname"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("email"),rs.getDate("regdate")));
            }
            return list;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
    public int loginOk(String id,String pwd) {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int n=0;
        try {
            con=DbcpBean.getConn();
            String sql = "select * from member where id = ? and pwd = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rs=pstmt.executeQuery();
            if(rs.next()) {
                n=1;
            }
            return n;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
    public int checkId(String id) {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int n=1;
        try {
            con=DbcpBean.getConn();
            String sql = "select * from member where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs=pstmt.executeQuery();
            if(rs.next()) {
                n=-1;
            }
            return n;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
    public int insert(MemberVo vo) {
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            con=DbcpBean.getConn();
            String sql="insert into member values(?,?,?,?,?,?,sysdate)";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPwd());
            pstmt.setString(3, vo.getName());
            pstmt.setString(4, vo.getNickname());
            pstmt.setString(5, vo.getPhone());
            pstmt.setString(6, vo.getEmail());
            int n = pstmt.executeUpdate();
            return n;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return -1;
        }finally {
            DbcpBean.closeConn(con, pstmt, null);
        }
    }
    
    public int update(MemberVo vo) {
    	 Connection con=null;
         PreparedStatement pstmt=null;
         try {
             con=DbcpBean.getConn();
             String sql="update member set pwd=?, name=?,nickname=?,phone=?,email=? where id=?";
             pstmt=con.prepareStatement(sql);
             pstmt.setString(1, vo.getPwd());
             pstmt.setString(2, vo.getName());
             pstmt.setString(3, vo.getNickname());
             pstmt.setString(4, vo.getPhone());
             pstmt.setString(5, vo.getEmail());
             pstmt.setString(6, vo.getId());
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
