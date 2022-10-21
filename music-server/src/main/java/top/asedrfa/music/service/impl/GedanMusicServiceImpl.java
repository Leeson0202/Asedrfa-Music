package top.asedrfa.music.service.impl;

import top.asedrfa.music.entity.GedanMusic;
import top.asedrfa.music.dao.GedanMusicDao;
import top.asedrfa.music.service.GedanMusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GedanMusic)表服务实现类
 *
 * @author makejava
 * @since 2021-09-11 00:34:23
 */
@Service("gedanMusicService")
public class GedanMusicServiceImpl implements GedanMusicService {
    @Resource
    private GedanMusicDao gedanMusicDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gdId 主键
     * @return 实例对象
     */
    @Override
    public List<GedanMusic> queryById(String gdId) {
        return this.gedanMusicDao.queryById(gdId);
    }

    @Override
    public GedanMusic queryByGedanMId(String gdId, String mId) {
        return gedanMusicDao.queryByGedanMId(gdId,mId);
    }

    @Override
    public List<GedanMusic> queryByMId(String mId) {
        List<GedanMusic> gedanMusics = gedanMusicDao.queryByMId(mId);
        return gedanMusics;
    }


    /**
     * 新增数据
     *
     * @param gedanMusic 实例对象
     * @return 实例对象
     */
    @Override
    public GedanMusic insert(GedanMusic gedanMusic) {
        this.gedanMusicDao.insert(gedanMusic);
        return gedanMusic;
    }


    /**
     * 通过主键删除数据
     *
     * @param gdId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String gdId) {
        return this.gedanMusicDao.deleteById(gdId) > 0;
    }

    @Override
    public boolean deleteByIdMId(String gdId, String mId) {
        return gedanMusicDao.deleteByIdMId(gdId, mId) > 0;
    }
}