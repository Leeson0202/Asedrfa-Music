package top.asedrfa.music.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (TLoad)实体类
 *
 * @author makejava
 * @since 2021-09-08 00:20:15
 */
@ApiModel(value = "登录信息实体类")
public class TLoad implements Serializable {
    private static final long serialVersionUID = 850889649018870761L;
    /**
     * 登录id
     */
    @ApiModelProperty("登录id")
    private String lId;
    /**
     * 应用id
     */
    @ApiModelProperty("应用id")
    private String appId;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String uId;
    /**
     * 随机数
     */
    @ApiModelProperty("随机数")
    private String msg;

    @JsonProperty("lId")
    public String getLId() {
        return lId;
    }

    public void setLId(String lId) {
        this.lId = lId;
    }

    @JsonProperty("appId")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("uId")
    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}