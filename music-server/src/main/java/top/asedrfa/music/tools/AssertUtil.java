package top.asedrfa.music.tools;


import top.asedrfa.music.exception.ParamsException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssertUtil {

    private static String rootPath = "E:/music/static/";
//    private static String rootPath = "/home/leeson/music/static/";

    // 获取文件目录
    public static String getRootPath() {
        File targetFile = new File(rootPath);
        if (!targetFile.exists())
            return "/home/leeson/music/static/";
        else
            return rootPath;
    }

    public static void isTrue(Boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }

    public static Boolean strIsEmpty(String msg) {
        if (msg.trim().isEmpty() || msg == null) {
            return true;
        } else return false;
    }

    public static Boolean strEqualsStr(String msg1, String msg2) {
        if (!msg1.trim().isEmpty() && msg1.equals(msg2))
            return true;
        else return false;
    }

    public static Map<String, Object> returnMap(Integer state, Object msg) {
        return returnMap(state, msg, null);
    }

    public static Map<String, Object> returnMap(Integer state, Object msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", state);
        map.put("msg", msg);
        if (data != null) {
            map.put("data", data);
        }
        return map;
    }

}
