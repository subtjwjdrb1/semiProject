package semi.vo;

import java.sql.Date;

public class ReviewVo {
	private int rno;
	private String rtitle;
	private String rcontent;
	private Date rdate;
	private int rhit;
	private int rgrade;
	private String orgfilename;
	private String savefilename;
	private String id;
	private int company;
	private int rreport;
	private int recommend;
	public ReviewVo() {}
    public ReviewVo(int rno, String rtitle, String rcontent, Date rdate, int rhit, int rgrade, String orgfilename,
            String savefilename, String id, int company, int rreport, int recommend) {
        super();
        this.rno = rno;
        this.rtitle = rtitle;
        this.rcontent = rcontent;
        this.rdate = rdate;
        this.rhit = rhit;
        this.rgrade = rgrade;
        this.orgfilename = orgfilename;
        this.savefilename = savefilename;
        this.id = id;
        this.company = company;
        this.rreport = rreport;
        this.recommend = recommend;
    }
    public int getRno() {
        return rno;
    }
    public void setRno(int rno) {
        this.rno = rno;
    }
    public String getRtitle() {
        return rtitle;
    }
    public void setRtitle(String rtitle) {
        this.rtitle = rtitle;
    }
    public String getRcontent() {
        return rcontent;
    }
    public void setRcontent(String rcontent) {
        this.rcontent = rcontent;
    }
    public Date getRdate() {
        return rdate;
    }
    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }
    public int getRhit() {
        return rhit;
    }
    public void setRhit(int rhit) {
        this.rhit = rhit;
    }
    public int getRgrade() {
        return rgrade;
    }
    public void setRgrade(int rgrade) {
        this.rgrade = rgrade;
    }
    public String getOrgfilename() {
        return orgfilename;
    }
    public void setOrgfilename(String orgfilename) {
        this.orgfilename = orgfilename;
    }
    public String getSavefilename() {
        return savefilename;
    }
    public void setSavefilename(String savefilename) {
        this.savefilename = savefilename;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCompany() {
        return company;
    }
    public void setCompany(int company) {
        this.company = company;
    }
    public int getRreport() {
        return rreport;
    }
    public void setRreport(int rreport) {
        this.rreport = rreport;
    }
    public int getRecommend() {
        return recommend;
    }
    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }
}
