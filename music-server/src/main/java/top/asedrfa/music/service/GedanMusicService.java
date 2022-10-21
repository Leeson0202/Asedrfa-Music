package top.asedrfa.music.service;

import top.asedrfa.music.entity.GedanMusic;

import java.util.List;

/**
 * (GedanMusic)表服务接口
 *
 * @author makejava
 * @since 2021-09-11 00:34:23
 */
public interface GedanMusicService {

    /**
     * 通过ID查询单条数据
     *
     * @param gdId 主键
     * @return 实例对象
     */
    List<GedanMusic> queryById(String gdId);
    // 查找具体数据 歌单音乐数据
    GedanMusic queryByGedanMId(String gdId,String mId);
    // 通过 音乐查找记录
    List<GedanMusic> queryByMId(String mId);



    /**
     * 新增数据
     *
     * @param gedanMusic 实例对象
     * @return 实例对象
     */
    GedanMusic insert(GedanMusic gedanMusic);


    /**
     * 通过主键删除数据
     *
     * @param gdId 主键
     * @return 是否成功
     */
    boolean deleteById(String gdId);

    // 删除一条记录
    boolean deleteByIdMId(String gdId, String mId);

}