package top.asedrfa.music.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-09-07 20:11:02
 */
public class User implements Serializable {
    private static final long serialVersionUID = -21989827541892115L;
    /**
     * 用户id
     */
    private String uId;
    /**
     * 账户
     */
    private String usr;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 电话
     */
    private String tel;
    /**
     * 昵称
     */
    private String nName;
    /**
     * 真实姓名
     */
    private String rName;
    /**
     * 头像链接
     */
    private String hUrl;
    /**
     * 喜爱歌单id
     */
    private String gdId;
    /**
     * 创建时间
     */
    private Date usrDate;

    @Override
    public String toString() {
        return "User{" +
                "uId='" + uId + '\'' +
                ", usr='" + usr + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tel='" + tel + '\'' +
                ", nName='" + nName + '\'' +
                ", rName='" + rName + '\'' +
                ", hUrl='" + hUrl + '\'' +
                ", gdId='" + gdId + '\'' +
                ", usrDate=" + usrDate +
                '}';
    }

    @JsonProperty("uId")
    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }
    @JsonProperty("usr")
    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }
    @JsonProperty("pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @JsonProperty("tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @JsonProperty("nName")
    public String getNName() {
        return nName;
    }

    public void setNName(String nName) {
        this.nName = nName;
    }
    @JsonProperty("rName")
    public String getRName() {
        return rName;
    }

    public void setRName(String rName) {
        this.rName = rName;
    }
    @JsonProperty("hUrl")
    public String getHUrl() {
        return hUrl;
    }

    public void setHUrl(String hUrl) {
        this.hUrl = hUrl;
    }
    @JsonProperty("gdId")
    public String getGdId() {
        return gdId;
    }

    public void setGdId(String gdId) {
        this.gdId = gdId;
    }
    @JsonProperty("usrDate")
    public Date getUsrDate() {
        return usrDate;
    }

    public void setUsrDate(Date usrDate) {
        this.usrDate = usrDate;
    }


}