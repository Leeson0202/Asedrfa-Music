package top.asedrfa.music.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (GedanMusic)实体类
 *
 * @author makejava
 * @since 2021-09-11 00:34:23
 */
@ApiModel(value = "歌单音乐实体类")
public class GedanMusic implements Serializable {
    private static final long serialVersionUID = 653008509599367330L;
    /**
     * 歌单id
     */
    @ApiModelProperty("歌单id")
    private String gdId;
    /**
     * 音乐id
     */
    @ApiModelProperty("音乐id")
    private String mId;

    @JsonProperty("gdId")
    public String getGdId() {
        return gdId;
    }

    public void setGdId(String gdId) {
        this.gdId = gdId;
    }

    @JsonProperty("mId")
    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

}