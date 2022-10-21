package top.asedrfa.music.service;

import top.asedrfa.music.entity.Music;

import java.util.List;

/**
 * (Music)表服务接口
 *
 * @author makejava
 * @since 2021-09-11 00:16:53
 */
public interface MusicService {

    // 根据歌名查找
    List<Music> queryByMName(String mName);

    /**
     * 通过ID查询单条数据
     *
     * @param mId 主键
     * @return 实例对象
     */
    Music queryById(String mId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Music> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    Music insert(Music music);

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    Music update(Music music);

    /**
     * 通过主键删除数据
     *
     * @param mId 主键
     * @return 是否成功
     */
    boolean deleteById(String mId);

}