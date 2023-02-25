package semi.vo;

import java.sql.Date;

public class NoticesVo {
    private int num;
    private String title;
    private String content;
    private int hit;
    private Date ndate;
    public NoticesVo() {}
    public NoticesVo(int num, String title, String content, int hit, Date ndate) {
        super();
        this.num = num;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.ndate = ndate;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getHit() {
        return hit;
    }
    public void setHit(int hit) {
        this.hit = hit;
    }
    public Date getNdate() {
        return ndate;
    }
    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }
}
