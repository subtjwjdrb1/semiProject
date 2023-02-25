package semi.vo;

import java.sql.Date;

public class MyPageVo {
	private int sno;
	private String title;
	private String id;
	private String url;
	private Date sdate;
	private int rno;
	
	
	public MyPageVo(int sno, String title, String id, String url, Date sdate, int rno) {
		super();
		this.sno = sno;
		this.title = title;
		this.id = id;
		this.url = url;
		this.sdate = sdate;
		this.rno = rno;
	}
	
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
}
