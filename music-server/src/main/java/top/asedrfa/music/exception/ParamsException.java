package top.asedrfa.music.exception;

public class ParamsException extends RuntimeException {
    private Integer code = 500;
    private String msg = "参数异常！";

    public ParamsException() {
        super("参数异常！");
    }

    public ParamsException(String msg) {
        super((msg));
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}