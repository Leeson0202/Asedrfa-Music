package top.asedrfa.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import top.asedrfa.music.entity.*;
import top.asedrfa.music.service.GedanMusicService;
import top.asedrfa.music.service.GedanService;
import org.springframework.web.bind.annotation.*;
import top.asedrfa.music.service.UserService;
import top.asedrfa.music.tools.AssertUtil;
import top.asedrfa.music.tools.UpLoadFileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * (Gedan)表控制层
 *
 * @author makejava
 * @since 2021-09-08 15:15:53
 */
@Api
@RestController
@RequestMapping("gedan")
@CrossOrigin
public class GedanController {
    /**
     * 服务对象
     */
    @Resource
    private GedanService gedanService;
    @Resource
    private UserService userService;
    @Resource
    private GedanMusicService gedanMusicService;

    //删除歌单中的歌曲  gdId mId appId msg
    @PostMapping("deletemusic")
    @ApiOperation(value = "删除歌单中的歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gdId", value = "歌单Id", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "mId", value = "音乐Id", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> deletemusic(GedanMusic gedanMusic, @RequestParam("appId") String appId, @RequestParam("msg") String msg) throws Exception {
        // 1. 验证登录
        TLoad login = userService.isLogin(appId, msg);
        AssertUtil.isTrue(login == null, "未登录");
        // 2. 判断歌单是否存在且 是否已经插入 ，是该用户的？
        GetGedan tempgetGedan = gedanService.queryById(gedanMusic.getGdId());
        AssertUtil.isTrue(tempgetGedan == null, "gdId错误");
        // 是否已经插入
        GedanMusic gedanMusic1 = gedanMusicService.queryByGedanMId(gedanMusic.getGdId(), gedanMusic.getMId());
        AssertUtil.isTrue(gedanMusic1 == null, "歌曲未在歌单内");
        // 3. 删除 gedan_music 中的记录
        AssertUtil.isTrue(!gedanMusicService.deleteByIdMId(gedanMusic.getGdId(), gedanMusic.getMId()), "移除失败");
        // 4. 更新music数量
        Gedan gedan = new Gedan();
        gedan.setGdId(tempgetGedan.getGdId());
        gedan.setMNum(tempgetGedan.getmNum() - 1);
        gedanService.update(gedan);
        // 4. 返回歌单
        return AssertUtil.returnMap(200, "移除成功", gedanService.queryById(gedanMusic.getGdId()));
    }


    //添加歌曲到歌单  gdId mId appId msg
    @PostMapping("insertmusic")
    @ApiOperation(value = "添加歌曲到歌单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gdId", value = "歌单Id", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "mId", value = "音乐Id", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")})
    public Map<String, Object> insertmusic(GedanMusic gedanMusic, @RequestParam("appId") String appId, @RequestParam("msg") String msg) throws Exception {
        // 1. 验证登录
        TLoad login = userService.isLogin(appId, msg);
        AssertUtil.isTrue(login == null, "未登录");
        // 2. 判断歌单是否存在且 是否已经插入 ，是该用户的？
        GetGedan tempgetGedan = gedanService.queryById(gedanMusic.getGdId());
        AssertUtil.isTrue(tempgetGedan == null, "gdId错误");
        // 是否已经插入
        GedanMusic gedanMusic1 = gedanMusicService.queryByGedanMId(gedanMusic.getGdId(), gedanMusic.getMId());
        AssertUtil.isTrue(gedanMusic1 != null, "歌曲已存在");
        // 3. 添加到gedan_music
        AssertUtil.isTrue(gedanMusicService.insert(gedanMusic) == null, "添加失败");
        // 4. 更新music数量
        Gedan gedan = new Gedan();
        gedan.setGdId(tempgetGedan.getGdId());
        gedan.setMNum(tempgetGedan.getmNum() + 1);
        gedanService.update(gedan);
        // 4. 返回歌单
        return AssertUtil.returnMap(200, "添加成功", gedanService.queryById(gedanMusic.getGdId()));
    }


    //删除歌单    gdId appId  msg
    @PostMapping("delete")
    @ApiOperation(value = "删除歌单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gdId", value = "歌单Id", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")})
    public Map<String, Object> delete(String gdId, @RequestParam("appId") String appId, @RequestParam("msg") String msg) throws Exception {
        // 1. 验证登录
        TLoad login = userService.isLogin(appId, msg);
        AssertUtil.isTrue(login == null, "未登录");
        // 2. 删除歌单
        // 2.1 删除歌单里的音乐记录
        gedanMusicService.deleteById(gdId);
        // 2.2 删除歌单
        gedanService.deleteById(gdId);
        return AssertUtil.returnMap(200, "删除成功");
    }


    // 修改歌单   gdId gdName  gdFm(img)  appId  msg
    @PostMapping("update")
    @ApiOperation(value = "修改歌单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gdId", value = "歌单Id", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "gdName", value = "歌单名字", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "img", value = "歌单封面", dataType = "__file", paramType = "form"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")})
    public Map<String, Object> update(Gedan gd, MultipartFile img, @RequestParam("appId") String appId, @RequestParam("msg") String msg, HttpServletRequest request) throws Exception {
        // 1. 验证登录
        TLoad login = userService.isLogin(appId, msg);
        AssertUtil.isTrue(login == null, "未登录");
        // 2. 是否有封面
        if (img != null) {
            String gdFm = UserController.getUrlHead(request) + UpLoadFileUtil.uploadImg(img, "/users/files/img/");
            gd.setGdFm(gdFm);
        }
        // 3. 更新歌单封面
        GetGedan newgd = gedanService.update(gd);
        Map<String, Object> map = AssertUtil.returnMap(200, "歌单修改成功", newgd);

        return map;
    }


    // 创建歌单  gdName img appId  msg
    @PostMapping("insert")
    @ApiOperation(value = "创建歌单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gdName", value = "歌单名字", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "img", value = "歌单封面", dataType = "__file", paramType = "form"),
            @ApiImplicitParam(name = "appId", value = "appId", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "msg", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")})
    public Map<String, Object> insert(Gedan gd, MultipartFile img, @RequestParam("appId") String appId, @RequestParam("msg") String msg, HttpServletRequest request) throws Exception {
        // 1. 验证登录
        TLoad login = userService.isLogin(appId, msg);
        AssertUtil.isTrue(login == null, "未登录");
        // 2. 是否有该歌单
        List<GetGedan> getGedans = gedanService.queryByUId(login.getUId());
        for (GetGedan getGedan : getGedans) {
            AssertUtil.isTrue(getGedan.getGdName() == gd.getGdName(), "歌单明命相同");
        }
        // 3. 修改歌单的 Uid
        gd.setUId(login.getUId());  // Uid

        // 4. 是否有封面
        if (img != null) {
            String gdFm = UserController.getUrlHead(request) + UpLoadFileUtil.uploadImg(img, "/users/files/img/");
            gd.setGdFm(gdFm);
        }
        // 5. 插入歌单
        GetGedan getGedan = gedanService.insert(gd);
        Map<String, Object> map = AssertUtil.returnMap(200, "歌单创建成功", getGedan);

        return map;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param gdId 主键
     * @return 单条数据
     */
    @GetMapping("selectById")
    @ApiOperation(value = "通过gdId单条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gdId", value = "登录msg", required = true, dataTypeClass = String.class, paramType = "query")})
    public Map<String, Object> selectByGedanId(String gdId) {
        GetGedan getgedan = this.gedanService.queryById(gdId);
        return AssertUtil.returnMap(200, "查找成功", getgedan);
    }

}