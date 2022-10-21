package top.asedrfa.music.service.impl;

import top.asedrfa.music.entity.Gedan;
import top.asedrfa.music.dao.GedanDao;
import top.asedrfa.music.entity.GedanMusic;
import top.asedrfa.music.entity.GetGedan;
import top.asedrfa.music.entity.Music;
import top.asedrfa.music.service.GedanService;
import org.springframework.stereotype.Service;
import top.asedrfa.music.tools.AssertUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Gedan)表服务实现类
 *
 * @author makejava
 * @since 2021-09-08 15:15:53
 */
@Service("gedanService")
public class GedanServiceImpl implements GedanService {
    @Resource
    private GedanDao gedanDao;
    @Resource
    private MusicServiceImpl musicService;
    @Resource
    private GedanMusicServiceImpl gedanMusicService;

    // 通过 uId 查找
    @Override
    public List<GetGedan> queryByUId(String uId) {
        List<GetGedan> getGedans = gedanDao.queryByUId(uId);
        for (GetGedan getGedan : getGedans) {
            List<Music> musics = new ArrayList<>();
            // 1. 提取mId
            List<GedanMusic> gedanMusics = gedanMusicService.queryById(getGedan.getGdId());
            // 2. 获取musics
            for (GedanMusic gedanMusic : gedanMusics) {
                Music music = musicService.queryById(gedanMusic.getMId());
                musics.add(music);
            }
            getGedan.setMusics(musics);
        }
        return getGedans;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param gdId 主键
     * @return 实例对象
     */
    @Override
    public GetGedan queryById(String gdId) {
        // 1. 提取信息
        GetGedan getGedan = gedanDao.queryById(gdId);
        List<Music> musics = new ArrayList<>();
        // 2. 提取歌单的 mId信息
        List<GedanMusic> gedanMusics = gedanMusicService.queryById(getGedan.getGdId());
        // 3. 获取musics
        for (GedanMusic gedanMusic : gedanMusics) {
            Music music = musicService.queryById(gedanMusic.getMId());
            musics.add(music);
        }
        getGedan.setMusics(musics);

        return getGedan;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Gedan> queryAllByLimit(int offset, int limit) {
        return this.gedanDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param gedan 实例对象
     * @return 实例对象
     */
    @Override
    public GetGedan insert(Gedan gedan) {
        // gdId
        gedan.setGdId(UUID.randomUUID().toString());
        // MNum
        gedan.setMNum(0);
        // date
        if (gedan.getGdFm() == null) {
            gedan.setGdFm("/users/files/img/fm01.jpg");
        }
        gedan.setGdDate(new Date());
        AssertUtil.isTrue(gedanDao.insert(gedan) < 1, "插入歌单失败");
        GetGedan getGedan = this.queryById(gedan.getGdId());
        return getGedan;
    }

    /**
     * 修改数据
     *
     * @param gedan 实例对象
     * @return 实例对象
     */
    @Override
    public GetGedan update(Gedan gedan) {
        AssertUtil.isTrue(gedanDao.update(gedan) < 1, "歌单更新失败");
        GetGedan getgedan = this.queryById(gedan.getGdId());
        return getgedan;
    }

    /**
     * 通过主键删除数据
     *
     * @param gdId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String gdId) {
        return this.gedanDao.deleteById(gdId) > 0;
    }
}