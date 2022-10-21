package top.asedrfa.music.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import java.util.Date;

@ApiModel(value = "歌单实体类")
public class GetGedan {
    /**
     * 歌单id
     */
    @ApiModelProperty("歌单id")
    private String gdId;
    /**
     * 歌单名称
     */
    @ApiModelProperty("歌单名称")
    private String gdName;
    /**
     * 歌单封面
     */
    @ApiModelProperty("歌单封面")
    private String gdFm;
    /**
     * 歌曲数量
     */
    @ApiModelProperty("歌曲数量")
    private Integer mNum;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date gdDate;
    // 音乐列表
    @ApiModelProperty("音乐列表")
    private List<Music> musics;


    public GetGedan() {
    }

    public GetGedan(String gdId, String gdName, String gdFm, Integer mNum, Date gdDate, List<Music> musics) {
        this.gdId = gdId;
        this.gdName = gdName;
        this.gdFm = gdFm;
        this.mNum = mNum;
        this.gdDate = gdDate;
        this.musics = musics;
    }


    @JsonProperty("musics")
    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }


    @JsonProperty("gdId")
    public String getGdId() {
        return gdId;
    }

    public void setGdId(String gdId) {
        this.gdId = gdId;
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
    public Integer getmNum() {
        return mNum;
    }

    public void setmNum(Integer mNum) {
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
