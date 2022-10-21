package top.asedrfa.music.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * (Music)实体类
 *
 * @author makejava
 * @since 2021-09-11 00:16:53
 */
@Data
@ApiModel(value = "音乐实体类")
public class Music implements Serializable {
    private static final long serialVersionUID = 874589555326171346L;
    /**
     * 音乐id
     */
    @ApiModelProperty("音乐id")
    private String mId;
    /**
     * 歌手
     */
    @ApiModelProperty("歌手")
    private String singer;
    /**
     * 音乐链接
     */
    @ApiModelProperty("音乐链接")
    private String mUrl;
    /**
     * 歌词链接
     */
    @ApiModelProperty("歌词链接")
    private String gcUrl;
    /**
     * 封面链接
     */
    @ApiModelProperty("封面链接")
    private String fUrl;
    /**
     * 歌名
     */
    @ApiModelProperty("歌名")
    private String mName;

    public Music(String mId, String singer, String mUrl, String mName) {
        this.mId = mId;
        this.singer = singer;
        this.mUrl = mUrl;
        this.mName = mName;
    }


}