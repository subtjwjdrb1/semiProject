package semi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.BuyVo;
import semi.vo.FqboardVo;
import semi.vo.ReviewVo;
import semi.vo.SellVo;
import test.dbcp.DbcpBean;

public class ReportDao {
    private static ReportDao instance  = new ReportDao();
    private ReportDao() {}
    public static ReportDao getInstance() {
        return instance;
    }
    public FqboardVo fqboardDetail(int fqno) {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
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
                FqboardVo vo=new FqboardVo(rs.getInt("fqno"), fqtype, fqtitle, fqcontent, null, fqhit, 0, fqreport, id, recommend);
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
    public BuyVo buyDetail(int bno) {
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
   
    public int buyDelete(String sql) {
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
    
    public int buyGetMax() {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select NVL(count(bno),0) cnt from buy where breport > 4";
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
    public ArrayList<BuyVo> buyReport(int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            //System.out.println("con:"+con);
            String sql="select * from (select aa.*,rownum rnum from(select * from buy where breport>4 order by breport desc , bno desc)aa ) where rnum>=? and rnum<=?";
            pstmt=con.prepareStatement(sql);
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
        }catch(SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
   
    public int sellDelete(String sql) {
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
    public int fqboardGetMax() {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select NVL(count(bqno),0) cnt from fqboard where fqreport > 4";
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
    public int sellGetMax() {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select NVL(count(sno),0) cnt from sell where sreport > 4";
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
    } public SellVo sellDetail(int sno) {
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
    public ArrayList<SellVo> sellReport(int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            //System.out.println("con:"+con);
            String sql="select * from (select aa.*,rownum rnum from(select * from sell where sreport>4 order by sreport desc , sno desc)aa ) where rnum>=? and rnum<=?";
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
    public ArrayList<FqboardVo> fqboardReport(int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            //System.out.println("con:"+con);
            String sql="select * from (select aa.*,rownum rnum from(select * from fqboard where fqreport>4 order by fqreport desc , fqno desc)aa ) where rnum>=? and rnum<=?";
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
            return list;
        }catch(SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
    public ArrayList<ReviewVo> ReviewReport(int startRow,int endRow){
        Connection conn = null;
        PreparedStatement pstmt=null;
        ResultSet rs= null;
        try {
            conn=DbcpBean.getConn();
            String sql="select * from (select aa.*,rownum rnum from(select * from review where rreport > 4 order by rreport desc , rno desc)aa) where rnum>=? and rnum<=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<ReviewVo> list=new ArrayList<>();
            while(rs.next()) {
                ReviewVo vo=new ReviewVo(rs.getInt("rno"),rs.getString("rtitle"),rs.getString("rcontent"),rs.getDate("rdate"),rs.getInt("rhit"),rs.getInt("rgrade"),rs.getString("orgfilename"),rs.getString("savefilename"),rs.getString("id"),rs.getInt("company"),rs.getInt("rreport"),rs.getInt("recommend"));
                list.add(vo);
            }
            return list;
        }catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }finally {
            DbcpBean.closeConn(conn, pstmt, rs);
        }
    }
    public int reviewGetMax() {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select NVL(count(rno),0) cnt from review where rreport > 4";
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
    
   
    
    public ArrayList<SellVo>SellReport(int startRow,int endRow){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql= "select * from(" + 
                    "  select aa.*,rownum rnum from (" + 
                    "   select * from sell where sreport>4"+
                    "        order by sreport desc , sno desc" + 
                    "   )aa" + 
                    ") where rnum>=? and rnum<=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs=pstmt.executeQuery();
            ArrayList<SellVo> list =new  ArrayList<SellVo>();
            while(rs.next()) {
                SellVo vo=new SellVo(rs.getInt("sno"),rs.getInt("os"),rs.getInt("telecom"),
                        rs.getInt("company"),rs.getString("loc"),rs.getInt("price"),
                        rs.getString("stitle"),rs.getString("scontent"),rs.getDate("sdate"),
                        rs.getInt("sgrade"),rs.getInt("shit"),rs.getInt("success"),rs.getInt("sreport"),rs.getString("id"));
                list.add(vo);
            }
            return list;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        } finally {
            DbcpBean.closeConn(con, pstmt, rs);
        }
    }
    public ReviewVo reviewDetail(int rno) {
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=DbcpBean.getConn();
            String sql="select * from review where rno=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1, rno);
            rs=pstmt.executeQuery();
            if(rs.next()) {
                int rnum=rs.getInt("rno");
                String rtitle=rs.getString("rtitle");
                String rcontent=rs.getString("rcontent");
                Date rdate=rs.getDate("rdate");
                int rhit = rs.getInt("rhit");
                int rgrade=rs.getInt("rgrade");
                int rreport = rs.getInt("rreport");
                String orgfilename = rs.getString("orgfilename");
                String savefilename = rs.getString("savefilename");
                String id=rs.getString("id");
                int company=rs.getInt("company");
                int recommend=rs.getInt("recommend");
                ReviewVo vo=new ReviewVo(rnum, rtitle, rcontent, rdate, rhit, rgrade, orgfilename, savefilename, id, company,0,0);
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
    public int reportDelete(String sql) {
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
