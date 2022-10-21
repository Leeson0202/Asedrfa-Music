package top.asedrfa.music.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    /**
     * 全局异常处理 返回json要加@ResponseBody
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionhander(Exception e) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(e.getMessage());
        map.put("code",500);
        map.put("msg","false1");
        return map;
    }

    /**
     * 全局异常处理 返回json要加@ResponseBody
     *
     * @return
     */
    @ExceptionHandler(value = ParamsException.class)
    @ResponseBody
    public Map<String, Object> paramsexceptionhander(ParamsException p) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", p.getCode());
        map.put("msg", p.getMsg());
        return map;
    }
}
