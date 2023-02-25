package semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import test.dbcp.DbcpBean;

public class AdminDao {
    private static AdminDao instance  = new AdminDao();
    private AdminDao() {}
    public static AdminDao getInstance() {
        return instance;
    }
    public int notices(String table , String title,String content) {
        Connection con=null;
        PreparedStatement pstmt=null;
            try {
                con=DbcpBean.getConn();
                String sql="";
                System.out.println("table:"+table);
                if(table.equals("notices")) {
                    sql="insert into "+table+" values(notices_seq.nextval,?,?,0,sysdate)";
                }else if(table.equals("buy")) {
                	sql="insert into "+table+" values(buy_seq.nextval,?,?,sysdate,2,0,0,0,'admin')";
                }else if(table.equals("fqboard")) {
                	sql="insert into "+table+" values(fq_seq.nextval,0,?,?,sysdate,0,2,0,'admin',0)";
                }else if(table.equals("sell")) {
                    sql="insert into "+table+" values(sell_seq.nextval,0,0,0,'°øÁö',0,?,?,sysdate,2,0,0,0,'admin')";
                }
                pstmt=con.prepareStatement(sql);
                pstmt.setString(1, title);
                pstmt.setString(2, content);
                System.out.println("sql : "+sql);
                return pstmt.executeUpdate();
            } catch (SQLException se) {
                System.out.println(se.getMessage());
                return -1;
            }finally {
                DbcpBean.closeConn(con, pstmt, null);
            }
    }
    public int adminDelete(String sql) {
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
