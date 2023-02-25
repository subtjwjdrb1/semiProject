package semi.vo;

import java.sql.Date;

public class FqcommentVo {
	public int fqcno;
	public String fqcontent;
	public int fqcref;
	public int fqclev;
	public int fqcstep;
	public int fqcreport;
	public Date fqcdate;
	public int fqno;
	public String id;
	
	public FqcommentVo() {}

	public FqcommentVo(int fqcno, String fqcontent, int fqcref, int fqclev, int fqcstep, int fqcreport, Date fqcdate,
			int fqno, String id) {
		super();
		this.fqcno = fqcno;
		this.fqcontent = fqcontent;
		this.fqcref = fqcref;
		this.fqclev = fqclev;
		this.fqcstep = fqcstep;
		this.fqcreport = fqcreport;
		this.fqcdate = fqcdate;
		this.fqno = fqno;
		this.id = id;
	}

	public int getFqcno() {
		return fqcno;
	}

	public void setFqcno(int fqcno) {
		this.fqcno = fqcno;
	}

	public String getFqcontent() {
		return fqcontent;
	}

	public void setFqcontent(String fqcontent) {
		this.fqcontent = fqcontent;
	}

	public int getFqcref() {
		return fqcref;
	}

	public void setFqcref(int fqcref) {
		this.fqcref = fqcref;
	}

	public int getFqclev() {
		return fqclev;
	}

	public void setFqclev(int fqclev) {
		this.fqclev = fqclev;
	}

	public int getFqcstep() {
		return fqcstep;
	}

	public void setFqcstep(int fqcstep) {
		this.fqcstep = fqcstep;
	}

	public int getFqcreport() {
		return fqcreport;
	}

	public void setFqcreport(int fqcreport) {
		this.fqcreport = fqcreport;
	}

	public Date getFqcdate() {
		return fqcdate;
	}

	public void setFqcdate(Date fqcdate) {
		this.fqcdate = fqcdate;
	}

	public int getFqno() {
		return fqno;
	}

	public void setFqno(int fqno) {
		this.fqno = fqno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
