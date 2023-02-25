package semi.vo;

import java.sql.Date;

public class MemberVo {
    private String id;
    private String pwd;
    private String nickname;
    private String name;
    private String phone;
    private String email;
    private Date regdate;
    public MemberVo(){}
    public MemberVo(String id, String pwd, String nickname, String name, String phone, String email, Date regdate) {
        super();
        this.id = id;
        this.pwd = pwd;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.regdate = regdate;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getRegdate() {
        return regdate;
    }
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
