package semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.vo.ScommentVo;
import test.dbcp.DbcpBean;

public class ScommentDao {
	private static ScommentDao instance=new ScommentDao();
	private ScommentDao() {}
	public static ScommentDao getInstance() {
		return instance;
	}
	
	public ArrayList<ScommentVo> list(int sno){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DbcpBean.getConn();
			String sql="select * from scomment where sno=? order by scref asc, scno asc, sclev asc, scstep asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs=pstmt.executeQuery();
			ArrayList<ScommentVo> list=new ArrayList<>();
			while(rs.next()) {
				String sccontent=rs.getString("sccontent");
				//System.out.println("sccontent:" + sccontent);
				int ref=rs.getInt("scref");
				int lev=rs.getInt("sclev");
				int step=rs.getInt("scstep");
				int scno=rs.getInt("scno");
				//System.out.println("scno:" + scno);
				String id=rs.getString("id");
				
				ScommentVo vo=new ScommentVo(scno, sccontent, ref, lev, step, 0, null, sno, id);
				list.add(vo);
			}
			//System.out.println(list);
			return list;
			
		}catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DbcpBean.closeConn(con, pstmt, rs);
		}		
				
	}
	
	public int insert(ScommentVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=DbcpBean.getConn();
			int scno=vo.getScno();
			int lev=vo.getSclev();
			int step=vo.getScstep();
			if(scno!=0) {
				String sql="update scomment set scstep=scstep+1 where scref=? and scstep<?";
				pstmt1=con.prepareStatement(sql);
				pstmt1.setInt(1, vo.getScref());
				pstmt1.setInt(2, step);
				pstmt1.executeUpdate();
				lev=lev+1;
				step=step+1;
				String sql1 = "insert into scomment values(scno_seq.nextval,?,?,?,?,0,sysdate,?,?)";
				pstmt=con.prepareStatement(sql1);
				pstmt.setString(1, vo.getSccontent());
				pstmt.setInt(2, vo.getScref());
				pstmt.setInt(3, lev);
				pstmt.setInt(4, step);
				pstmt.setInt(5,vo.getSno());
				pstmt.setString(6, vo.getId());
				pstmt.executeUpdate();
			}else {
				String sql="insert into scomment values(scno_seq.nextval,?,scno_seq.currval,?,?,0,sysdate,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, vo.getSccontent());
				pstmt.setInt(2,vo.getSclev());
				pstmt.setInt(3,vo.getScstep());
				pstmt.setInt(4,vo.getSno());
				pstmt.setString(5, vo.getId());
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
