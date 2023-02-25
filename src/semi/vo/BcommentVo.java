package semi.vo;

import java.sql.Date;

public class BcommentVo {
	private int bcno;
	private String bccontent;
	private int bcref;
	private int bclev;
	private int bcstep;
	private int bcreport;
	private Date bcdate;
	private String id;
	private int bno;
	
	public BcommentVo() {}

	public BcommentVo(int bcno, String bccontent, int bcref, int bclev, int bcstep, int bcreport, Date bcdate,
			String id, int bno) {
		super();
		this.bcno = bcno;
		this.bccontent = bccontent;
		this.bcref = bcref;
		this.bclev = bclev;
		this.bcstep = bcstep;
		this.bcreport = bcreport;
		this.bcdate = bcdate;
		this.id = id;
		this.bno = bno;
	}

	public int getBcno() {
		return bcno;
	}

	public void setBcno(int bcno) {
		this.bcno = bcno;
	}

	public String getBccontent() {
		return bccontent;
	}

	public void setBccontent(String bccontent) {
		this.bccontent = bccontent;
	}

	public int getBcref() {
		return bcref;
	}

	public void setBcref(int bcref) {
		this.bcref = bcref;
	}

	public int getBclev() {
		return bclev;
	}

	public void setBclev(int bclev) {
		this.bclev = bclev;
	}

	public int getBcstep() {
		return bcstep;
	}

	public void setBcstep(int bcstep) {
		this.bcstep = bcstep;
	}

	public int getBcreport() {
		return bcreport;
	}

	public void setBcreport(int bcreport) {
		this.bcreport = bcreport;
	}

	public Date getBcdate() {
		return bcdate;
	}

	public void setBcdate(Date bcdate) {
		this.bcdate = bcdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}
	
	
}
