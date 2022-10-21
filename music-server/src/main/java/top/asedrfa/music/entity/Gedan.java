package top.asedrfa.music.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.io.Serializable;

/**
 * (Gedan)实体类
 *
 * @author makejava
 * @since 2021-09-08 15:15:53
 */
public class Gedan implements Serializable {
    private static final long serialVersionUID = 588948058230198233L;
    /**
     * 歌单id
     */
    private String gdId;
    /**
     * 用户id
     */
    private String uId;
    /**
     * 歌单名称
     */
    private String gdName;
    /**
     * 歌单封面
     */
    private String gdFm;
    /**
     * 歌曲数量
     */
    private Integer mNum;
    /**
     * 创建时间
     */
    private Date gdDate;

    public Gedan() {
    }

    public Gedan(String gdId, String uId, String gdName, String gdFm, Integer mNum, Date gdDate) {
        this.gdId = gdId;
        this.uId = uId;
        this.gdName = gdName;
        this.gdFm = gdFm;
        this.mNum = mNum;
        this.gdDate = gdDate;
    }

    @JsonProperty("gdId")
    public String getGdId() {
        return gdId;
    }

    public void setGdId(String gdId) {
        this.gdId = gdId;
    }

    @JsonProperty("uId")
    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    @JsonProperty("gdName")
    public String getGdName() {
        return gdName;
    }

    public void setGdName(String gdName) {
        this.gdName = gdName;
    }

    @JsonProperty("gdFm")
    public String getGdFm() {
        return gdFm;
    }

    public void setGdFm(String gdFm) {
        this.gdFm = gdFm;
    }

    @JsonProperty("mNum")
    public Integer getMNum() {
        return mNum;
    }

    public void setMNum(Integer mNum) {
        this.mNum = mNum;
    }

    @JsonProperty("gdDate")
    public Date getGdDate() {
        return gdDate;
    }

    public void setGdDate(Date gdDate) {
        this.gdDate = gdDate;
    }

}