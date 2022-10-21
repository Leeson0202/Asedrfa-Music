package top.asedrfa.music.service.impl;

import top.asedrfa.music.entity.GedanMusic;
import top.asedrfa.music.entity.Music;
import top.asedrfa.music.dao.MusicDao;
import top.asedrfa.music.service.GedanMusicService;
import top.asedrfa.music.service.MusicService;
import org.springframework.stereotype.Service;
import top.asedrfa.music.tools.AssertUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Music)表服务实现类
 *
 * @author makejava
 * @since 2021-09-11 00:16:53
 */
@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicDao musicDao;
    @Resource
    private GedanMusicService gedanMusicService;

    @Override
    public List<Music> queryByMName(String mName) {
        List<Music> musics = musicDao.queryByMName("%" + mName + "%");
        return musics;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param mId 主键
     * @return 实例对象
     */
    @Override
    public Music queryById(String mId) {
        return this.musicDao.queryById(mId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Music> queryAllByLimit(int offset, int limit) {
        return this.musicDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    @Override
    public Music insert(Music music) {
        this.musicDao.insert(music);
        return music;
    }

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    @Override
    public Music update(Music music) {
        this.musicDao.update(music);
        return this.queryById(music.getMId());
    }

    /**
     * 通过主键删除数据
     *
     * @param mId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String mId) {
        // 1. 查询是否有该 歌曲
        Music music = this.queryById(mId);
        AssertUtil.isTrue(music == null, "无该歌曲");
        // 2. 删除 歌单-歌曲  记录
        List<GedanMusic> gedanMusics = gedanMusicService.queryByMId(mId);
        for (GedanMusic gedanMusic : gedanMusics) { //删除所有记录
            AssertUtil.isTrue(!gedanMusicService.deleteByIdMId(gedanMusic.getGdId(), gedanMusic.getMId()), "删除歌单-歌曲记录错误");
        }
        // 3. 删除歌曲
        return this.musicDao.deleteById(mId) > 0;
    }
}