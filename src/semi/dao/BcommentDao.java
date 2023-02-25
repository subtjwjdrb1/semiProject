package semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.BcommentVo;
import semi.vo.ScommentVo;
import test.dbcp.DbcpBean;

public class BcommentDao {
	private static BcommentDao instance=new BcommentDao();
	private BcommentDao() {}
	public static BcommentDao getInstance() {
		return instance;
	}
	
	public ArrayList<BcommentVo> list(int bno){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select * from bcomment where bno=? order by bcref asc, bcno asc, bclev asc, bcstep asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			ArrayList<BcommentVo> list=new ArrayList<>();
			while(rs.next()) {
				String bccontent=rs.getString("bccontent");
				int ref=rs.getInt("bcref");
				int lev=rs.getInt("bclev");
				int step=rs.getInt("bcstep");
				int bcno=rs.getInt("bcno");
				String id=rs.getString("id");
				
				BcommentVo vo=new BcommentVo(bcno, bccontent, ref, lev, step, 0, null, id, bno);
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
	
	public int insert(BcommentVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=DbcpBean.getConn();
			int scno=vo.getBcno();
			int lev=vo.getBclev();
			int step=vo.getBcstep();
			if(scno!=0) {
				String sql="update bcomment set bcstep=bcstep+1 where bcref=? and bcstep<?";
				pstmt1=con.prepareStatement(sql);
				pstmt1.setInt(1, vo.getBcref());
				pstmt1.setInt(2, step);
				pstmt1.executeUpdate();
				lev=lev+1;
				step=step+1;
				String sql1 = "insert into bcomment values(bcno_seq.nextval,?,?,?,?,0,sysdate,?,?)";
				pstmt=con.prepareStatement(sql1);
				pstmt.setString(1, vo.getBccontent());
				pstmt.setInt(2, vo.getBcref());
				pstmt.setInt(3, lev);
				pstmt.setInt(4, step);
				pstmt.setString(5, vo.getId());
				pstmt.setInt(6,vo.getBno());
				pstmt.executeUpdate();
			}else {
				String sql="insert into bcomment values(bcno_seq.nextval,?,bcno_seq.currval,?,?,0,sysdate,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, vo.getBccontent());
				pstmt.setInt(2,vo.getBclev());
				pstmt.setInt(3,vo.getBcstep());
				pstmt.setString(4, vo.getId());
				pstmt.setInt(5,vo.getBno());
				pstmt.executeUpdate();
			}
			
			return 1;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}
	
}
