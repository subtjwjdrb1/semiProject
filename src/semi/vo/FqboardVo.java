package semi.vo;

import java.sql.Date;

public class FqboardVo {
	private int fqno;
	private int fqtype;
	private String fqtitle;
	private String fqcontent;
	private Date fqdate;
	private int fqhit;
	private int fqgrade;
	private int fqreport;
	private String id;
	private int recommend;
	
	public FqboardVo() {}
	
	public FqboardVo(int fqno, int fqtype, String fqtitle, String fqcontent, Date fqdate, int fqhit, int fqgrade,
			int fqreport, String id, int recommend) {
		super();
		this.fqno = fqno;
		this.fqtype = fqtype;
		this.fqtitle = fqtitle;
		this.fqcontent = fqcontent;
		this.fqdate = fqdate;
		this.fqhit = fqhit;
		this.fqgrade = fqgrade;
		this.fqreport = fqreport;
		this.id = id;
		this.recommend = recommend;
	}

	public int getFqno() {
		return fqno;
	}

	public void setFqno(int fqno) {
		this.fqno = fqno;
	}

	public int getFqtype() {
		return fqtype;
	}

	public void setFqtype(int fqtype) {
		this.fqtype = fqtype;
	}

	public String getFqtitle() {
		return fqtitle;
	}

	public void setFqtitle(String fqtitle) {
		this.fqtitle = fqtitle;
	}

	public String getFqcontent() {
		return fqcontent;
	}

	public void setFqcontent(String fqcontent) {
		this.fqcontent = fqcontent;
	}

	public Date getFqdate() {
		return fqdate;
	}

	public void setFqdate(Date fqdate) {
		this.fqdate = fqdate;
	}

	public int getFqhit() {
		return fqhit;
	}

	public void setFqhit(int fqhit) {
		this.fqhit = fqhit;
	}

	public int getFqgrade() {
		return fqgrade;
	}

	public void setFqgrade(int fqgrade) {
		this.fqgrade = fqgrade;
	}

	public int getFqreport() {
		return fqreport;
	}

	public void setFqreport(int fqreport) {
		this.fqreport = fqreport;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
}
