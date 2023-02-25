package semi.vo;
import java.sql.Date;
public class RcommentVo {
    private int rcno;
    private String rccontent;
    private int rcref;
    private int rclev;
    private int rcstep;
    private int rcreport;
    private Date rcdate;
    private String id;
    private int rno;
    public RcommentVo() {}
    public RcommentVo(int rcno, String rccontent, int rcref, int rclev, int rcstep, int rcreport, Date rcdate, String id,
            int rno) {
        super();
        this.rcno = rcno;
        this.rccontent = rccontent;
        this.rcref = rcref;
        this.rclev = rclev;
        this.rcstep = rcstep;
        this.rcreport = rcreport;
        this.rcdate = rcdate;
        this.id = id;
        this.rno = rno;
    }
    public int getRcno() {
        return rcno;
    }
    public void setRcno(int rcno) {
        this.rcno = rcno;
    }
    public String getRccontent() {
        return rccontent;
    }
    public void setRccontent(String rccontent) {
        this.rccontent = rccontent;
    }
    public int getRcref() {
        return rcref;
    }
    public void setRcref(int rcref) {
        this.rcref = rcref;
    }
    public int getRclev() {
        return rclev;
    }
    public void setRclev(int rclev) {
        this.rclev = rclev;
    }
    public int getRcstep() {
        return rcstep;
    }
    public void setRcstep(int rcstep) {
        this.rcstep = rcstep;
    }
    public int getRcreport() {
        return rcreport;
    }
    public void setRcreport(int rcreport) {
        this.rcreport = rcreport;
    }
    public Date getRcdate() {
        return rcdate;
    }
    public void setRcdate(Date rcdate) {
        this.rcdate = rcdate;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getRno() {
        return rno;
    }
    public void setRno(int rno) {
        this.rno = rno;
    }
}
