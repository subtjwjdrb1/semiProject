package semi.vo;

import java.sql.Date;

public class ScommentVo {
	private int scno;
	private String sccontent;
	private int scref;
	private int sclev;
	private int scstep;
	private int screport;
	private Date scdate;
	private int sno;
	private String id;
	
	public ScommentVo () {}

	public ScommentVo(int scno, String sccontent, int scref, int sclev, int scstep, int screport, Date scdate, int sno,
			String id) {
		super();
		this.scno = scno;
		this.sccontent = sccontent;
		this.scref = scref;
		this.sclev = sclev;
		this.scstep = scstep;
		this.screport = screport;
		this.scdate = scdate;
		this.sno = sno;
		this.id = id;
	}

	public int getScno() {
		return scno;
	}

	public void setScno(int scno) {
		this.scno = scno;
	}

	public String getSccontent() {
		return sccontent;
	}

	public void setSccontent(String sccontent) {
		this.sccontent = sccontent;
	}

	public int getScref() {
		return scref;
	}

	public void setScref(int scref) {
		this.scref = scref;
	}

	public int getSclev() {
		return sclev;
	}

	public void setSclev(int sclev) {
		this.sclev = sclev;
	}

	public int getScstep() {
		return scstep;
	}

	public void setScstep(int scstep) {
		this.scstep = scstep;
	}

	public int getScReport() {
		return screport;
	}

	public void setScReport(int screport) {
		this.screport = screport;
	}

	public Date getScdate() {
		return scdate;
	}

	public void setScdate(Date scdate) {
		this.scdate = scdate;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
