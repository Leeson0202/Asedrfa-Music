package top.asedrfa.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import top.asedrfa.music.dao.GedanDao;
import top.asedrfa.music.dao.TLoadDao;
import top.asedrfa.music.entity.GetGedan;
import top.asedrfa.music.entity.GetUser;
import top.asedrfa.music.entity.TLoad;
import top.asedrfa.music.entity.User;
import top.asedrfa.music.service.GedanService;
import top.asedrfa.music.service.MusicService;
import top.asedrfa.music.service.TLoadService;
import top.asedrfa.music.service.UserService;
import org.springframework.web.bind.annotation.*;
import top.asedrfa.music.service.impl.MusicServiceImpl;
import top.asedrfa.music.tools.AssertUtil;
import top.asedrfa.music.tools.UpLoadFileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-09-07 20:11:02
 */
@Api
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private TLoadService tLoadService;
    @Resource
    private GedanService gedanService;
    @Resource
    private TLoadDao tLoadDao;

    // 退出登录 appId msg
    @PostMapping("deleteTLoad")
    @ApiOperation(value = "退出登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> deleteTLoad(@RequestParam("appId") String appId, @RequestParam("msg") String msg) {
        // 1. 判断是否登录
        TLoad tLoaded = userService.isLogin(appId, msg);
        AssertUtil.isTrue(tLoaded == null, "信息错误");
        // 2. 删除登录记录
        AssertUtil.isTrue(!tLoadService.deleteById(tLoaded.getLId()), "退出失败");
        return AssertUtil.returnMap(200, "退出成功");
    }


    // 更改密码  pwd  appid  msg
    @PostMapping("updatepwd")
    @ApiOperation(value = "更改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> upatepwd(@RequestParam("pwd") String pwd, @RequestParam("appId") String appId, @RequestParam("msg") String msg, HttpServletRequest request) throws Exception {
        // 1. 判断是否登录
        TLoad tLoaded = userService.isLogin(appId, msg);
        AssertUtil.isTrue(tLoaded == null, "信息错误");
        // 2. 更新密码
        userService.updatepwd(tLoaded, pwd);
        return AssertUtil.returnMap(200, "修改成功");
    }

    // 修改头像  file appId  msg
    @PostMapping("updatehead")
    @ApiOperation(value = "修改头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "头像 png/jpg", required = true, dataType = "__file", paramType = "form"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> upateHead(@RequestParam("file") MultipartFile file, @RequestParam("appId") String appId, @RequestParam("msg") String msg, HttpServletRequest request) throws Exception {
        // 1. 判断是否登录
        TLoad tLoaded = userService.isLogin(appId, msg);
        AssertUtil.isTrue(tLoaded == null, "信息错误");
        // 2. 上传图片
        String hUrl = getUrlHead(request) + UpLoadFileUtil.uploadImg(file, "users/head/");

        // 3. 修改用户头像url
        User user = userService.queryById(tLoaded.getUId());
        user.setHUrl(hUrl);
        // 更新url
        userService.updatehead(user);
        // 4. 返回信息
        return AssertUtil.returnMap(200, "上传成功", hUrl);
    }

    //修改账户： 昵称 账户 手机号 真实姓名
    //user(tel必须)  appId  msg
    //修改用户信息
    @PostMapping("update")
    @ApiOperation(value = "修改账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "电话", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "nName", value = "昵称", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "usr", value = "账户", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "rName", value = "真实姓名", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> updateUser(User user, @RequestParam("appId") String appId, @RequestParam("msg") String msg) {
        GetUser update = userService.update(user, appId, msg);
        return AssertUtil.returnMap(200, "更新成功", update);
    }

    //获取用户信息  sessionCode=appId+msg
    @PostMapping("getuser")
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> getUser(@RequestParam("appId") String appId, @RequestParam("msg") String msg) {
        GetUser user = userService.getUser(appId, msg);
        return AssertUtil.returnMap(200, "获取成功", user);
    }

    // 找回密码 tel pwd code
    @PostMapping("findpwd")
    @ApiOperation(value = "找回密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "新密码", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> findpwd(@RequestParam("tel") String tel, @RequestParam("pwd") String pwd, @RequestParam("code") String code, HttpSession session) throws Exception {
        // 获得 session中的 验证码code
        String sessioncode = (String) session.getAttribute("code");
        session.removeAttribute("code");// 删除 验证码 code
        AssertUtil.isTrue(!sessioncode.equalsIgnoreCase(code), "验证码错误");  // 对比code
        userService.findpwd(tel, pwd);
        return AssertUtil.returnMap(200, "修改成功");

    }

    // 注册  tel  pwd
    @PostMapping("insert")
    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> insert(User user) {
        userService.insert(user);
        return AssertUtil.returnMap(200, "regist success");
    }


    //登录  usr pwd appId(放在tLoad)
    // 用usr作为账户登录（usr可以是自己设定的，也可以是手机号）
    @PostMapping("login")
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usr", value = "账号或者手机号", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> login(TLoad tLoad, @RequestParam("usr") String usr, @RequestParam("pwd") String pwd) {
        tLoad = userService.login(tLoad, usr, pwd);
        return AssertUtil.returnMap(200, tLoad.getMsg());

    }

    @PostMapping("del")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号", required = true, dataTypeClass = String.class, paramType = "query"),

    })
    public Map<String, Object> delete(@RequestParam("tel") String tel) {
        // 1. 判断是否有该用户
        User user = userService.queryByTel(tel);
        AssertUtil.isTrue(user == null, "null");
        // 2. 获取歌单 并删除歌单
        List<GetGedan> getGedans = gedanService.queryByUId(user.getUId());
        for (GetGedan getGedan : getGedans) {
            AssertUtil.isTrue(!gedanService.deleteById(getGedan.getGdId()), "歌单删除失败");
        }

        // 3. 删除登录信息
        TLoad tLoad = tLoadDao.queryByuId(user.getUId());
        if (tLoad != null)
            AssertUtil.isTrue(!tLoadService.deleteById(tLoad.getLId()), "登录信息删除失败");

        // 4. 删除用户
        AssertUtil.isTrue(!userService.deleteById(user.getUId()), "删除失败");


        return AssertUtil.returnMap(200, "删除成功");

    }

    public static String getUrlHead(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

}