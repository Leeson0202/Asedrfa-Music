package top.asedrfa.music.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-09-07 20:11:02
 */
@ApiModel(value = "用户实体类")
public class GetUser implements Serializable {
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nName;
    /**
     * 头像链接
     */
    @ApiModelProperty("头像链接")
    private String hUrl;
    /**
     * 账户
     */
    @ApiModelProperty("账户")
    private String usr;
    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String tel;
    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String rName;
    /**
     * 喜爱歌单id
     */
    @ApiModelProperty("喜爱歌单的id")
    private String gdId;

    // 我的歌单列表
    @ApiModelProperty("我的歌单列表")
    private List<GetGedan> gedanList;

    @JsonProperty("nName")
    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    @JsonProperty("hUrl")
    public String gethUrl() {
        return hUrl;
    }

    public void sethUrl(String hUrl) {
        this.hUrl = hUrl;
    }

    @JsonProperty("usr")
    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    @JsonProperty("tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @JsonProperty("rName")
    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    @JsonProperty("gdId")
    public String getGdId() {
        return gdId;
    }

    public void setGdId(String gdId) {
        this.gdId = gdId;
    }

    @JsonProperty("gedanList")
    public List<GetGedan> getGedanList() {
        return gedanList;
    }

    public void setGedanList(List<GetGedan> gedanList) {
        this.gedanList = gedanList;
    }
}