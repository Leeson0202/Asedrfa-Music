package top.asedrfa.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import top.asedrfa.music.entity.Music;
import top.asedrfa.music.service.MusicService;
import org.springframework.web.bind.annotation.*;
import top.asedrfa.music.tools.AssertUtil;
import top.asedrfa.music.tools.UpLoadFileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (Music)表控制层
 *
 * @author makejava
 * @since 2021-09-11 00:16:53
 */
@Api
@RestController
@RequestMapping("music")
@CrossOrigin
public class MusicController {
    /**
     * 服务对象
     */
    @Resource
    private MusicService musicService;

    // 更新歌曲 mId ----mName singer   file(.mp3)   gc(.lrc) fm(.png)
    @PostMapping("update")
    @ApiOperation(value = "更新歌曲，想更新哪一个就该哪一个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mId", value = "音乐Id", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "mName", value = "音乐名称", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "singer", value = "歌手", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "file", value = "mp3文件", dataType = "ile", paramType = "form"),
            @ApiImplicitParam(name = "gc", value = "歌词lrc文件", dataType = "file", paramType = "form"),
            @ApiImplicitParam(name = "fm", value = "封面png/jpg文件", dataType = "file", paramType = "form"),
    })
    public Map<String, Object> update(Music music, MultipartFile file, MultipartFile gc, MultipartFile fm, HttpServletRequest request) throws Exception {
        // 1. 判断歌曲是否存在
        AssertUtil.isTrue(music.getMId() == null, "缺少mId");
        AssertUtil.isTrue(musicService.queryById(music.getMId()) == null, "mId错误");

        // 2. 判断是否有歌曲、歌词和封面
        if (file != null) {
            String mUrl = UserController.getUrlHead(request) + UpLoadFileUtil.uploadFile(file, "music/sources/");
            music.setMUrl(mUrl);
        }
        if (gc != null) {  // 歌词
            String gcUrl = UserController.getUrlHead(request) + UpLoadFileUtil.uploadFile(gc, "music/temp/");
            music.setGcUrl(gcUrl);
        }
        if (fm != null) {  // 封面
            String fUrl = UserController.getUrlHead(request) + UpLoadFileUtil.uploadImg(fm, "music/img/");
            music.setFUrl(fUrl);
        }
        AssertUtil.isTrue(musicService.update(music) == null, "修改失败");
        music = musicService.queryById(music.getMId());
        return AssertUtil.returnMap(200, "修改成功", music);
    }


    // 删除歌曲  mId
    @PostMapping("delete")
    @ApiOperation(value = "删除歌单中的歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mId", value = "音乐Id", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> delete(String mId) throws Exception {
        AssertUtil.isTrue(!musicService.deleteById(mId), "删除失败");
        return AssertUtil.returnMap(200, "删除成功");
    }


    // 插入歌曲 mName singer file(.mp3) ---- gc(.txt) fm(.png)
    @PostMapping("insert")
    @ApiOperation(value = "插入歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mName", value = "音乐名称", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "singer", value = "歌手", required = true, dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "file", value = "mp3文件", required = true, dataType = "__file", paramType = "form"),
            @ApiImplicitParam(name = "gc", value = "歌词lrc文件", dataType = "__file", paramType = "form"),
            @ApiImplicitParam(name = "fm", value = "封面png/jpg文件", dataType = "__file", paramType = "form"),
    })
    public Map<String, Object> insert(@RequestParam("mName") String mName, @RequestParam("singer") String singer, @RequestParam("file") MultipartFile file, MultipartFile gc, MultipartFile fm, HttpServletRequest request) throws Exception {
        // 1. 上传音乐文件
        String mUrl = UserController.getUrlHead(request) + UpLoadFileUtil.uploadFile(file, "music/sources/");

        // 2. 实例Music
        Music music = new Music(UUID.randomUUID().toString(), singer, mUrl, mName);

        // 3. 判断是否有歌词和封面
        if (gc != null) {  // 歌词
            String gcUrl = UserController.getUrlHead(request) + UpLoadFileUtil.uploadFile(gc, "music/temp/");
            music.setGcUrl(gcUrl);
        }
        if (fm != null) {  // 封面
            String fUrl = UserController.getUrlHead(request) + UpLoadFileUtil.uploadImg(fm, "music/img/");
            music.setFUrl(fUrl);
        }

        // 4. 更新url
        Music insert = musicService.insert(music);

        // 5. 返回信息
        return AssertUtil.returnMap(200, "创建成功", music);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param mId 主键
     * @return 单条数据
     */
    @GetMapping("selectById")
    @ApiOperation(value = "通过 mId 查询单条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mId", value = "音乐Id", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> selectByMId(@RequestParam("mId") String mId) {
        Music music = this.musicService.queryById(mId);
        return AssertUtil.returnMap(200, "查询成功", music);
    }

    // 根据歌名查找
    @GetMapping("selectByName")
    @ApiOperation(value = "根据歌名查找")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mName", value = "音乐Id", required = true, dataTypeClass = String.class, paramType = "query")
    })
    public Map<String, Object> selectByMName(String mName) {
        List<Music> musics = musicService.queryByMName(mName);
        return AssertUtil.returnMap(200, "查询成功", musics);
    }
}