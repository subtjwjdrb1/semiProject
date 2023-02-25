package semi.vo;

import java.sql.Date;

public class SellVo {
	private int sno;
	private int os;
	private int telecom;
	private int company;
	private String loc;
	private int price;
	private String stitle;
	private String scontent;
	private Date sdate;
	private int sgrade;
	private int shit;
	private int success;
	private int sreport;
	private String id;
	
	@Override
	public String toString() {
		return "SellVo [sno=" + sno + ", os=" + os + ", telecom=" + telecom + ", company=" + company + ", loc=" + loc
				+ ", price=" + price + ", stitle=" + stitle + ", sdate=" + sdate
				+ ", sgrade=" + sgrade + ", shit=" + shit + ", success=" + success + ", sreport=" + sreport + ", id="
				+ id + "]";
	}

	public SellVo() {}
	
	public SellVo(int sno, int os, int telecom, int company, String loc, int price, String stitle, String scontent,
			Date sdate, int sgrade, int shit, int success, int sreport ,String id) {
		super();
		this.sno = sno;
		this.os = os;
		this.telecom = telecom;
		this.company = company;
		this.loc = loc;
		this.price = price;
		this.stitle = stitle;
		this.scontent = scontent;
		this.sdate = sdate;
		this.sgrade = sgrade;
		this.shit = shit;
		this.success = success;
		this.sreport = sreport;
		this.id = id;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getOs() {
		return os;
	}

	public void setOs(int os) {
		this.os = os;
	}

	public int getTelecom() {
		return telecom;
	}

	public void setTelecom(int telecom) {
		this.telecom = telecom;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getScontent() {
		return scontent;
	}

	public void setScontent(String scontent) {
		this.scontent = scontent;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public int getSgrade() {
		return sgrade;
	}

	public void setSgrade(int sgrade) {
		this.sgrade = sgrade;
	}

	public int getShit() {
		return shit;
	}

	public void setShit(int shit) {
		this.shit = shit;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	
	public int getSreport() {
		return sreport;
	}

	public void setSreport(int sreport) {
		this.sreport = sreport;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
	
	
}
