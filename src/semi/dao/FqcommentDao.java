package semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.FqcommentVo;
import test.dbcp.DbcpBean;

public class FqcommentDao {
	private static FqcommentDao instance=new FqcommentDao();
	private FqcommentDao() {}
	public static FqcommentDao getInstance() {
		return instance;
	}
	
	public ArrayList<FqcommentVo> list(int fqno){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select * from fqcomment where fqno=? order by fqcref asc, fqcno asc, fqclev asc, fqcstep asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, fqno);
			rs=pstmt.executeQuery();
			ArrayList<FqcommentVo> list=new ArrayList<>();
			while(rs.next()) {
				String fqcontent=rs.getString("fqcontent");
				int ref=rs.getInt("fqcref");
				int lev=rs.getInt("fqclev");
				int step=rs.getInt("fqcstep");
				int fqcno=rs.getInt("fqcno");
				String id=rs.getString("id");
				
				FqcommentVo vo=new FqcommentVo(fqcno, fqcontent, ref, lev, step, 0, null, fqcno, id);
				list.add(vo);
			}
			return list;
			
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}					
	}
	
	public int insert(FqcommentVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=DbcpBean.getConn();
			int fqcno=vo.getFqcno();
			int lev=vo.getFqclev();
			int step=vo.getFqcstep();
			if(fqcno!=0) {
				String sql="update fqcomment set fqcstep=fqcstep+1 where fqcref=? and fqcstep<?";
				pstmt1=con.prepareStatement(sql);
				pstmt1.setInt(1, vo.getFqcref());
				pstmt1.setInt(2, step);
				pstmt1.executeUpdate();
				lev=lev+1;
				step=step+1;
				String sql1 = "insert into fqcomment values(fqcno_seq.nextval,?,?,?,?,0,sysdate,?,?)";
				pstmt=con.prepareStatement(sql1);
				pstmt.setString(1, vo.getFqcontent());
				pstmt.setInt(2, vo.getFqcref());
				pstmt.setInt(3, lev);
				pstmt.setInt(4, step);
				pstmt.setInt(5,vo.getFqno());
				pstmt.setString(6, vo.getId());
				pstmt.executeUpdate();
			}else {
				String sql="insert into fqcomment values(fqcno_seq.nextval,?,fqcno_seq.currval,?,?,0,sysdate,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, vo.getFqcontent());
				pstmt.setInt(2,vo.getFqclev());
				pstmt.setInt(3,vo.getFqcstep());
				pstmt.setInt(4,vo.getFqno());
				pstmt.setString(5, vo.getId());
				pstmt.executeUpdate();
			}
			return 1;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
			DbcpBean.closeConn(null, pstmt1, null);
		}
	}
}
